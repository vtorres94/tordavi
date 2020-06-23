package mx.com.tordavi.web.rest;

import mx.com.tordavi.TordaviApp;
import mx.com.tordavi.domain.CReservacion;
import mx.com.tordavi.domain.CCliente;
import mx.com.tordavi.domain.CPasajero;
import mx.com.tordavi.domain.CCorrida;
import mx.com.tordavi.repository.CReservacionRepository;
import mx.com.tordavi.service.CReservacionService;
import mx.com.tordavi.service.dto.CReservacionDTO;
import mx.com.tordavi.service.mapper.CReservacionMapper;
import mx.com.tordavi.service.dto.CReservacionCriteria;
import mx.com.tordavi.service.CReservacionQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CReservacionResource} REST controller.
 */
@SpringBootTest(classes = TordaviApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class CReservacionResourceIT {

    private static final String DEFAULT_CLAVE_RESERVACION = "AAAAAAAAAA";
    private static final String UPDATED_CLAVE_RESERVACION = "BBBBBBBBBB";

    private static final Double DEFAULT_PRECIO = 1D;
    private static final Double UPDATED_PRECIO = 2D;
    private static final Double SMALLER_PRECIO = 1D - 1D;

    private static final Integer DEFAULT_NUM_PASAJEROS = 1;
    private static final Integer UPDATED_NUM_PASAJEROS = 2;
    private static final Integer SMALLER_NUM_PASAJEROS = 1 - 1;

    private static final String DEFAULT_COMENTARIOS = "AAAAAAAAAA";
    private static final String UPDATED_COMENTARIOS = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_USUARIO_CREACION = 999999999999L;
    private static final Long UPDATED_ID_USUARIO_CREACION = 999999999998L;
    private static final Long SMALLER_ID_USUARIO_CREACION = 999999999999L - 1L;

    private static final Instant DEFAULT_FECHA_CREACION = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FECHA_CREACION = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_ID_USUARIO_ACTUALIZACION = 999999999999L;
    private static final Long UPDATED_ID_USUARIO_ACTUALIZACION = 999999999998L;
    private static final Long SMALLER_ID_USUARIO_ACTUALIZACION = 999999999999L - 1L;

    private static final Instant DEFAULT_FECHA_ACTUALIZACION = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FECHA_ACTUALIZACION = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_NOTAS = "AAAAAAAAAA";
    private static final String UPDATED_NOTAS = "BBBBBBBBBB";

    private static final Integer DEFAULT_ESTATUS = 9;
    private static final Integer UPDATED_ESTATUS = 8;
    private static final Integer SMALLER_ESTATUS = 9 - 1;

    private static final Integer DEFAULT_BORRADO = 1;
    private static final Integer UPDATED_BORRADO = 0;
    private static final Integer SMALLER_BORRADO = 1 - 1;

    @Autowired
    private CReservacionRepository cReservacionRepository;

    @Autowired
    private CReservacionMapper cReservacionMapper;

    @Autowired
    private CReservacionService cReservacionService;

    @Autowired
    private CReservacionQueryService cReservacionQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCReservacionMockMvc;

    private CReservacion cReservacion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CReservacion createEntity(EntityManager em) {
        CReservacion cReservacion = new CReservacion()
            .claveReservacion(DEFAULT_CLAVE_RESERVACION)
            .precio(DEFAULT_PRECIO)
            .numPasajeros(DEFAULT_NUM_PASAJEROS)
            .comentarios(DEFAULT_COMENTARIOS)
            .idUsuarioCreacion(DEFAULT_ID_USUARIO_CREACION)
            .fechaCreacion(DEFAULT_FECHA_CREACION)
            .idUsuarioActualizacion(DEFAULT_ID_USUARIO_ACTUALIZACION)
            .fechaActualizacion(DEFAULT_FECHA_ACTUALIZACION)
            .notas(DEFAULT_NOTAS)
            .estatus(DEFAULT_ESTATUS)
            .borrado(DEFAULT_BORRADO);
        return cReservacion;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CReservacion createUpdatedEntity(EntityManager em) {
        CReservacion cReservacion = new CReservacion()
            .claveReservacion(UPDATED_CLAVE_RESERVACION)
            .precio(UPDATED_PRECIO)
            .numPasajeros(UPDATED_NUM_PASAJEROS)
            .comentarios(UPDATED_COMENTARIOS)
            .idUsuarioCreacion(UPDATED_ID_USUARIO_CREACION)
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .idUsuarioActualizacion(UPDATED_ID_USUARIO_ACTUALIZACION)
            .fechaActualizacion(UPDATED_FECHA_ACTUALIZACION)
            .notas(UPDATED_NOTAS)
            .estatus(UPDATED_ESTATUS)
            .borrado(UPDATED_BORRADO);
        return cReservacion;
    }

    @BeforeEach
    public void initTest() {
        cReservacion = createEntity(em);
    }

    @Test
    @Transactional
    public void createCReservacion() throws Exception {
        int databaseSizeBeforeCreate = cReservacionRepository.findAll().size();

        // Create the CReservacion
        CReservacionDTO cReservacionDTO = cReservacionMapper.toDto(cReservacion);
        restCReservacionMockMvc.perform(post("/api/c-reservacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cReservacionDTO)))
            .andExpect(status().isCreated());

        // Validate the CReservacion in the database
        List<CReservacion> cReservacionList = cReservacionRepository.findAll();
        assertThat(cReservacionList).hasSize(databaseSizeBeforeCreate + 1);
        CReservacion testCReservacion = cReservacionList.get(cReservacionList.size() - 1);
        assertThat(testCReservacion.getClaveReservacion()).isEqualTo(DEFAULT_CLAVE_RESERVACION);
        assertThat(testCReservacion.getPrecio()).isEqualTo(DEFAULT_PRECIO);
        assertThat(testCReservacion.getNumPasajeros()).isEqualTo(DEFAULT_NUM_PASAJEROS);
        assertThat(testCReservacion.getComentarios()).isEqualTo(DEFAULT_COMENTARIOS);
        assertThat(testCReservacion.getIdUsuarioCreacion()).isEqualTo(DEFAULT_ID_USUARIO_CREACION);
        assertThat(testCReservacion.getFechaCreacion()).isEqualTo(DEFAULT_FECHA_CREACION);
        assertThat(testCReservacion.getIdUsuarioActualizacion()).isEqualTo(DEFAULT_ID_USUARIO_ACTUALIZACION);
        assertThat(testCReservacion.getFechaActualizacion()).isEqualTo(DEFAULT_FECHA_ACTUALIZACION);
        assertThat(testCReservacion.getNotas()).isEqualTo(DEFAULT_NOTAS);
        assertThat(testCReservacion.getEstatus()).isEqualTo(DEFAULT_ESTATUS);
        assertThat(testCReservacion.getBorrado()).isEqualTo(DEFAULT_BORRADO);
    }

    @Test
    @Transactional
    public void createCReservacionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cReservacionRepository.findAll().size();

        // Create the CReservacion with an existing ID
        cReservacion.setId(1L);
        CReservacionDTO cReservacionDTO = cReservacionMapper.toDto(cReservacion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCReservacionMockMvc.perform(post("/api/c-reservacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cReservacionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CReservacion in the database
        List<CReservacion> cReservacionList = cReservacionRepository.findAll();
        assertThat(cReservacionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdUsuarioCreacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = cReservacionRepository.findAll().size();
        // set the field null
        cReservacion.setIdUsuarioCreacion(null);

        // Create the CReservacion, which fails.
        CReservacionDTO cReservacionDTO = cReservacionMapper.toDto(cReservacion);

        restCReservacionMockMvc.perform(post("/api/c-reservacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cReservacionDTO)))
            .andExpect(status().isBadRequest());

        List<CReservacion> cReservacionList = cReservacionRepository.findAll();
        assertThat(cReservacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFechaCreacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = cReservacionRepository.findAll().size();
        // set the field null
        cReservacion.setFechaCreacion(null);

        // Create the CReservacion, which fails.
        CReservacionDTO cReservacionDTO = cReservacionMapper.toDto(cReservacion);

        restCReservacionMockMvc.perform(post("/api/c-reservacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cReservacionDTO)))
            .andExpect(status().isBadRequest());

        List<CReservacion> cReservacionList = cReservacionRepository.findAll();
        assertThat(cReservacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = cReservacionRepository.findAll().size();
        // set the field null
        cReservacion.setEstatus(null);

        // Create the CReservacion, which fails.
        CReservacionDTO cReservacionDTO = cReservacionMapper.toDto(cReservacion);

        restCReservacionMockMvc.perform(post("/api/c-reservacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cReservacionDTO)))
            .andExpect(status().isBadRequest());

        List<CReservacion> cReservacionList = cReservacionRepository.findAll();
        assertThat(cReservacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBorradoIsRequired() throws Exception {
        int databaseSizeBeforeTest = cReservacionRepository.findAll().size();
        // set the field null
        cReservacion.setBorrado(null);

        // Create the CReservacion, which fails.
        CReservacionDTO cReservacionDTO = cReservacionMapper.toDto(cReservacion);

        restCReservacionMockMvc.perform(post("/api/c-reservacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cReservacionDTO)))
            .andExpect(status().isBadRequest());

        List<CReservacion> cReservacionList = cReservacionRepository.findAll();
        assertThat(cReservacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCReservacions() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList
        restCReservacionMockMvc.perform(get("/api/c-reservacions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cReservacion.getId().intValue())))
            .andExpect(jsonPath("$.[*].claveReservacion").value(hasItem(DEFAULT_CLAVE_RESERVACION)))
            .andExpect(jsonPath("$.[*].precio").value(hasItem(DEFAULT_PRECIO.doubleValue())))
            .andExpect(jsonPath("$.[*].numPasajeros").value(hasItem(DEFAULT_NUM_PASAJEROS)))
            .andExpect(jsonPath("$.[*].comentarios").value(hasItem(DEFAULT_COMENTARIOS)))
            .andExpect(jsonPath("$.[*].idUsuarioCreacion").value(hasItem(DEFAULT_ID_USUARIO_CREACION.intValue())))
            .andExpect(jsonPath("$.[*].fechaCreacion").value(hasItem(DEFAULT_FECHA_CREACION.toString())))
            .andExpect(jsonPath("$.[*].idUsuarioActualizacion").value(hasItem(DEFAULT_ID_USUARIO_ACTUALIZACION.intValue())))
            .andExpect(jsonPath("$.[*].fechaActualizacion").value(hasItem(DEFAULT_FECHA_ACTUALIZACION.toString())))
            .andExpect(jsonPath("$.[*].notas").value(hasItem(DEFAULT_NOTAS)))
            .andExpect(jsonPath("$.[*].estatus").value(hasItem(DEFAULT_ESTATUS)))
            .andExpect(jsonPath("$.[*].borrado").value(hasItem(DEFAULT_BORRADO)));
    }
    
    @Test
    @Transactional
    public void getCReservacion() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get the cReservacion
        restCReservacionMockMvc.perform(get("/api/c-reservacions/{id}", cReservacion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cReservacion.getId().intValue()))
            .andExpect(jsonPath("$.claveReservacion").value(DEFAULT_CLAVE_RESERVACION))
            .andExpect(jsonPath("$.precio").value(DEFAULT_PRECIO.doubleValue()))
            .andExpect(jsonPath("$.numPasajeros").value(DEFAULT_NUM_PASAJEROS))
            .andExpect(jsonPath("$.comentarios").value(DEFAULT_COMENTARIOS))
            .andExpect(jsonPath("$.idUsuarioCreacion").value(DEFAULT_ID_USUARIO_CREACION.intValue()))
            .andExpect(jsonPath("$.fechaCreacion").value(DEFAULT_FECHA_CREACION.toString()))
            .andExpect(jsonPath("$.idUsuarioActualizacion").value(DEFAULT_ID_USUARIO_ACTUALIZACION.intValue()))
            .andExpect(jsonPath("$.fechaActualizacion").value(DEFAULT_FECHA_ACTUALIZACION.toString()))
            .andExpect(jsonPath("$.notas").value(DEFAULT_NOTAS))
            .andExpect(jsonPath("$.estatus").value(DEFAULT_ESTATUS))
            .andExpect(jsonPath("$.borrado").value(DEFAULT_BORRADO));
    }


    @Test
    @Transactional
    public void getCReservacionsByIdFiltering() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        Long id = cReservacion.getId();

        defaultCReservacionShouldBeFound("id.equals=" + id);
        defaultCReservacionShouldNotBeFound("id.notEquals=" + id);

        defaultCReservacionShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCReservacionShouldNotBeFound("id.greaterThan=" + id);

        defaultCReservacionShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCReservacionShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllCReservacionsByClaveReservacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where claveReservacion equals to DEFAULT_CLAVE_RESERVACION
        defaultCReservacionShouldBeFound("claveReservacion.equals=" + DEFAULT_CLAVE_RESERVACION);

        // Get all the cReservacionList where claveReservacion equals to UPDATED_CLAVE_RESERVACION
        defaultCReservacionShouldNotBeFound("claveReservacion.equals=" + UPDATED_CLAVE_RESERVACION);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByClaveReservacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where claveReservacion not equals to DEFAULT_CLAVE_RESERVACION
        defaultCReservacionShouldNotBeFound("claveReservacion.notEquals=" + DEFAULT_CLAVE_RESERVACION);

        // Get all the cReservacionList where claveReservacion not equals to UPDATED_CLAVE_RESERVACION
        defaultCReservacionShouldBeFound("claveReservacion.notEquals=" + UPDATED_CLAVE_RESERVACION);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByClaveReservacionIsInShouldWork() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where claveReservacion in DEFAULT_CLAVE_RESERVACION or UPDATED_CLAVE_RESERVACION
        defaultCReservacionShouldBeFound("claveReservacion.in=" + DEFAULT_CLAVE_RESERVACION + "," + UPDATED_CLAVE_RESERVACION);

        // Get all the cReservacionList where claveReservacion equals to UPDATED_CLAVE_RESERVACION
        defaultCReservacionShouldNotBeFound("claveReservacion.in=" + UPDATED_CLAVE_RESERVACION);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByClaveReservacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where claveReservacion is not null
        defaultCReservacionShouldBeFound("claveReservacion.specified=true");

        // Get all the cReservacionList where claveReservacion is null
        defaultCReservacionShouldNotBeFound("claveReservacion.specified=false");
    }
                @Test
    @Transactional
    public void getAllCReservacionsByClaveReservacionContainsSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where claveReservacion contains DEFAULT_CLAVE_RESERVACION
        defaultCReservacionShouldBeFound("claveReservacion.contains=" + DEFAULT_CLAVE_RESERVACION);

        // Get all the cReservacionList where claveReservacion contains UPDATED_CLAVE_RESERVACION
        defaultCReservacionShouldNotBeFound("claveReservacion.contains=" + UPDATED_CLAVE_RESERVACION);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByClaveReservacionNotContainsSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where claveReservacion does not contain DEFAULT_CLAVE_RESERVACION
        defaultCReservacionShouldNotBeFound("claveReservacion.doesNotContain=" + DEFAULT_CLAVE_RESERVACION);

        // Get all the cReservacionList where claveReservacion does not contain UPDATED_CLAVE_RESERVACION
        defaultCReservacionShouldBeFound("claveReservacion.doesNotContain=" + UPDATED_CLAVE_RESERVACION);
    }


    @Test
    @Transactional
    public void getAllCReservacionsByPrecioIsEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where precio equals to DEFAULT_PRECIO
        defaultCReservacionShouldBeFound("precio.equals=" + DEFAULT_PRECIO);

        // Get all the cReservacionList where precio equals to UPDATED_PRECIO
        defaultCReservacionShouldNotBeFound("precio.equals=" + UPDATED_PRECIO);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByPrecioIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where precio not equals to DEFAULT_PRECIO
        defaultCReservacionShouldNotBeFound("precio.notEquals=" + DEFAULT_PRECIO);

        // Get all the cReservacionList where precio not equals to UPDATED_PRECIO
        defaultCReservacionShouldBeFound("precio.notEquals=" + UPDATED_PRECIO);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByPrecioIsInShouldWork() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where precio in DEFAULT_PRECIO or UPDATED_PRECIO
        defaultCReservacionShouldBeFound("precio.in=" + DEFAULT_PRECIO + "," + UPDATED_PRECIO);

        // Get all the cReservacionList where precio equals to UPDATED_PRECIO
        defaultCReservacionShouldNotBeFound("precio.in=" + UPDATED_PRECIO);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByPrecioIsNullOrNotNull() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where precio is not null
        defaultCReservacionShouldBeFound("precio.specified=true");

        // Get all the cReservacionList where precio is null
        defaultCReservacionShouldNotBeFound("precio.specified=false");
    }

    @Test
    @Transactional
    public void getAllCReservacionsByPrecioIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where precio is greater than or equal to DEFAULT_PRECIO
        defaultCReservacionShouldBeFound("precio.greaterThanOrEqual=" + DEFAULT_PRECIO);

        // Get all the cReservacionList where precio is greater than or equal to UPDATED_PRECIO
        defaultCReservacionShouldNotBeFound("precio.greaterThanOrEqual=" + UPDATED_PRECIO);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByPrecioIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where precio is less than or equal to DEFAULT_PRECIO
        defaultCReservacionShouldBeFound("precio.lessThanOrEqual=" + DEFAULT_PRECIO);

        // Get all the cReservacionList where precio is less than or equal to SMALLER_PRECIO
        defaultCReservacionShouldNotBeFound("precio.lessThanOrEqual=" + SMALLER_PRECIO);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByPrecioIsLessThanSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where precio is less than DEFAULT_PRECIO
        defaultCReservacionShouldNotBeFound("precio.lessThan=" + DEFAULT_PRECIO);

        // Get all the cReservacionList where precio is less than UPDATED_PRECIO
        defaultCReservacionShouldBeFound("precio.lessThan=" + UPDATED_PRECIO);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByPrecioIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where precio is greater than DEFAULT_PRECIO
        defaultCReservacionShouldNotBeFound("precio.greaterThan=" + DEFAULT_PRECIO);

        // Get all the cReservacionList where precio is greater than SMALLER_PRECIO
        defaultCReservacionShouldBeFound("precio.greaterThan=" + SMALLER_PRECIO);
    }


    @Test
    @Transactional
    public void getAllCReservacionsByNumPasajerosIsEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where numPasajeros equals to DEFAULT_NUM_PASAJEROS
        defaultCReservacionShouldBeFound("numPasajeros.equals=" + DEFAULT_NUM_PASAJEROS);

        // Get all the cReservacionList where numPasajeros equals to UPDATED_NUM_PASAJEROS
        defaultCReservacionShouldNotBeFound("numPasajeros.equals=" + UPDATED_NUM_PASAJEROS);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByNumPasajerosIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where numPasajeros not equals to DEFAULT_NUM_PASAJEROS
        defaultCReservacionShouldNotBeFound("numPasajeros.notEquals=" + DEFAULT_NUM_PASAJEROS);

        // Get all the cReservacionList where numPasajeros not equals to UPDATED_NUM_PASAJEROS
        defaultCReservacionShouldBeFound("numPasajeros.notEquals=" + UPDATED_NUM_PASAJEROS);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByNumPasajerosIsInShouldWork() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where numPasajeros in DEFAULT_NUM_PASAJEROS or UPDATED_NUM_PASAJEROS
        defaultCReservacionShouldBeFound("numPasajeros.in=" + DEFAULT_NUM_PASAJEROS + "," + UPDATED_NUM_PASAJEROS);

        // Get all the cReservacionList where numPasajeros equals to UPDATED_NUM_PASAJEROS
        defaultCReservacionShouldNotBeFound("numPasajeros.in=" + UPDATED_NUM_PASAJEROS);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByNumPasajerosIsNullOrNotNull() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where numPasajeros is not null
        defaultCReservacionShouldBeFound("numPasajeros.specified=true");

        // Get all the cReservacionList where numPasajeros is null
        defaultCReservacionShouldNotBeFound("numPasajeros.specified=false");
    }

    @Test
    @Transactional
    public void getAllCReservacionsByNumPasajerosIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where numPasajeros is greater than or equal to DEFAULT_NUM_PASAJEROS
        defaultCReservacionShouldBeFound("numPasajeros.greaterThanOrEqual=" + DEFAULT_NUM_PASAJEROS);

        // Get all the cReservacionList where numPasajeros is greater than or equal to UPDATED_NUM_PASAJEROS
        defaultCReservacionShouldNotBeFound("numPasajeros.greaterThanOrEqual=" + UPDATED_NUM_PASAJEROS);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByNumPasajerosIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where numPasajeros is less than or equal to DEFAULT_NUM_PASAJEROS
        defaultCReservacionShouldBeFound("numPasajeros.lessThanOrEqual=" + DEFAULT_NUM_PASAJEROS);

        // Get all the cReservacionList where numPasajeros is less than or equal to SMALLER_NUM_PASAJEROS
        defaultCReservacionShouldNotBeFound("numPasajeros.lessThanOrEqual=" + SMALLER_NUM_PASAJEROS);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByNumPasajerosIsLessThanSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where numPasajeros is less than DEFAULT_NUM_PASAJEROS
        defaultCReservacionShouldNotBeFound("numPasajeros.lessThan=" + DEFAULT_NUM_PASAJEROS);

        // Get all the cReservacionList where numPasajeros is less than UPDATED_NUM_PASAJEROS
        defaultCReservacionShouldBeFound("numPasajeros.lessThan=" + UPDATED_NUM_PASAJEROS);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByNumPasajerosIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where numPasajeros is greater than DEFAULT_NUM_PASAJEROS
        defaultCReservacionShouldNotBeFound("numPasajeros.greaterThan=" + DEFAULT_NUM_PASAJEROS);

        // Get all the cReservacionList where numPasajeros is greater than SMALLER_NUM_PASAJEROS
        defaultCReservacionShouldBeFound("numPasajeros.greaterThan=" + SMALLER_NUM_PASAJEROS);
    }


    @Test
    @Transactional
    public void getAllCReservacionsByComentariosIsEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where comentarios equals to DEFAULT_COMENTARIOS
        defaultCReservacionShouldBeFound("comentarios.equals=" + DEFAULT_COMENTARIOS);

        // Get all the cReservacionList where comentarios equals to UPDATED_COMENTARIOS
        defaultCReservacionShouldNotBeFound("comentarios.equals=" + UPDATED_COMENTARIOS);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByComentariosIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where comentarios not equals to DEFAULT_COMENTARIOS
        defaultCReservacionShouldNotBeFound("comentarios.notEquals=" + DEFAULT_COMENTARIOS);

        // Get all the cReservacionList where comentarios not equals to UPDATED_COMENTARIOS
        defaultCReservacionShouldBeFound("comentarios.notEquals=" + UPDATED_COMENTARIOS);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByComentariosIsInShouldWork() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where comentarios in DEFAULT_COMENTARIOS or UPDATED_COMENTARIOS
        defaultCReservacionShouldBeFound("comentarios.in=" + DEFAULT_COMENTARIOS + "," + UPDATED_COMENTARIOS);

        // Get all the cReservacionList where comentarios equals to UPDATED_COMENTARIOS
        defaultCReservacionShouldNotBeFound("comentarios.in=" + UPDATED_COMENTARIOS);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByComentariosIsNullOrNotNull() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where comentarios is not null
        defaultCReservacionShouldBeFound("comentarios.specified=true");

        // Get all the cReservacionList where comentarios is null
        defaultCReservacionShouldNotBeFound("comentarios.specified=false");
    }
                @Test
    @Transactional
    public void getAllCReservacionsByComentariosContainsSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where comentarios contains DEFAULT_COMENTARIOS
        defaultCReservacionShouldBeFound("comentarios.contains=" + DEFAULT_COMENTARIOS);

        // Get all the cReservacionList where comentarios contains UPDATED_COMENTARIOS
        defaultCReservacionShouldNotBeFound("comentarios.contains=" + UPDATED_COMENTARIOS);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByComentariosNotContainsSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where comentarios does not contain DEFAULT_COMENTARIOS
        defaultCReservacionShouldNotBeFound("comentarios.doesNotContain=" + DEFAULT_COMENTARIOS);

        // Get all the cReservacionList where comentarios does not contain UPDATED_COMENTARIOS
        defaultCReservacionShouldBeFound("comentarios.doesNotContain=" + UPDATED_COMENTARIOS);
    }


    @Test
    @Transactional
    public void getAllCReservacionsByIdUsuarioCreacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where idUsuarioCreacion equals to DEFAULT_ID_USUARIO_CREACION
        defaultCReservacionShouldBeFound("idUsuarioCreacion.equals=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cReservacionList where idUsuarioCreacion equals to UPDATED_ID_USUARIO_CREACION
        defaultCReservacionShouldNotBeFound("idUsuarioCreacion.equals=" + UPDATED_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByIdUsuarioCreacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where idUsuarioCreacion not equals to DEFAULT_ID_USUARIO_CREACION
        defaultCReservacionShouldNotBeFound("idUsuarioCreacion.notEquals=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cReservacionList where idUsuarioCreacion not equals to UPDATED_ID_USUARIO_CREACION
        defaultCReservacionShouldBeFound("idUsuarioCreacion.notEquals=" + UPDATED_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByIdUsuarioCreacionIsInShouldWork() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where idUsuarioCreacion in DEFAULT_ID_USUARIO_CREACION or UPDATED_ID_USUARIO_CREACION
        defaultCReservacionShouldBeFound("idUsuarioCreacion.in=" + DEFAULT_ID_USUARIO_CREACION + "," + UPDATED_ID_USUARIO_CREACION);

        // Get all the cReservacionList where idUsuarioCreacion equals to UPDATED_ID_USUARIO_CREACION
        defaultCReservacionShouldNotBeFound("idUsuarioCreacion.in=" + UPDATED_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByIdUsuarioCreacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where idUsuarioCreacion is not null
        defaultCReservacionShouldBeFound("idUsuarioCreacion.specified=true");

        // Get all the cReservacionList where idUsuarioCreacion is null
        defaultCReservacionShouldNotBeFound("idUsuarioCreacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCReservacionsByIdUsuarioCreacionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where idUsuarioCreacion is greater than or equal to DEFAULT_ID_USUARIO_CREACION
        defaultCReservacionShouldBeFound("idUsuarioCreacion.greaterThanOrEqual=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cReservacionList where idUsuarioCreacion is greater than or equal to (DEFAULT_ID_USUARIO_CREACION + 1)
        defaultCReservacionShouldNotBeFound("idUsuarioCreacion.greaterThanOrEqual=" + (DEFAULT_ID_USUARIO_CREACION + 1));
    }

    @Test
    @Transactional
    public void getAllCReservacionsByIdUsuarioCreacionIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where idUsuarioCreacion is less than or equal to DEFAULT_ID_USUARIO_CREACION
        defaultCReservacionShouldBeFound("idUsuarioCreacion.lessThanOrEqual=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cReservacionList where idUsuarioCreacion is less than or equal to SMALLER_ID_USUARIO_CREACION
        defaultCReservacionShouldNotBeFound("idUsuarioCreacion.lessThanOrEqual=" + SMALLER_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByIdUsuarioCreacionIsLessThanSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where idUsuarioCreacion is less than DEFAULT_ID_USUARIO_CREACION
        defaultCReservacionShouldNotBeFound("idUsuarioCreacion.lessThan=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cReservacionList where idUsuarioCreacion is less than (DEFAULT_ID_USUARIO_CREACION + 1)
        defaultCReservacionShouldBeFound("idUsuarioCreacion.lessThan=" + (DEFAULT_ID_USUARIO_CREACION + 1));
    }

    @Test
    @Transactional
    public void getAllCReservacionsByIdUsuarioCreacionIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where idUsuarioCreacion is greater than DEFAULT_ID_USUARIO_CREACION
        defaultCReservacionShouldNotBeFound("idUsuarioCreacion.greaterThan=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cReservacionList where idUsuarioCreacion is greater than SMALLER_ID_USUARIO_CREACION
        defaultCReservacionShouldBeFound("idUsuarioCreacion.greaterThan=" + SMALLER_ID_USUARIO_CREACION);
    }


    @Test
    @Transactional
    public void getAllCReservacionsByFechaCreacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where fechaCreacion equals to DEFAULT_FECHA_CREACION
        defaultCReservacionShouldBeFound("fechaCreacion.equals=" + DEFAULT_FECHA_CREACION);

        // Get all the cReservacionList where fechaCreacion equals to UPDATED_FECHA_CREACION
        defaultCReservacionShouldNotBeFound("fechaCreacion.equals=" + UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByFechaCreacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where fechaCreacion not equals to DEFAULT_FECHA_CREACION
        defaultCReservacionShouldNotBeFound("fechaCreacion.notEquals=" + DEFAULT_FECHA_CREACION);

        // Get all the cReservacionList where fechaCreacion not equals to UPDATED_FECHA_CREACION
        defaultCReservacionShouldBeFound("fechaCreacion.notEquals=" + UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByFechaCreacionIsInShouldWork() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where fechaCreacion in DEFAULT_FECHA_CREACION or UPDATED_FECHA_CREACION
        defaultCReservacionShouldBeFound("fechaCreacion.in=" + DEFAULT_FECHA_CREACION + "," + UPDATED_FECHA_CREACION);

        // Get all the cReservacionList where fechaCreacion equals to UPDATED_FECHA_CREACION
        defaultCReservacionShouldNotBeFound("fechaCreacion.in=" + UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByFechaCreacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where fechaCreacion is not null
        defaultCReservacionShouldBeFound("fechaCreacion.specified=true");

        // Get all the cReservacionList where fechaCreacion is null
        defaultCReservacionShouldNotBeFound("fechaCreacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCReservacionsByIdUsuarioActualizacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where idUsuarioActualizacion equals to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCReservacionShouldBeFound("idUsuarioActualizacion.equals=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cReservacionList where idUsuarioActualizacion equals to UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCReservacionShouldNotBeFound("idUsuarioActualizacion.equals=" + UPDATED_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByIdUsuarioActualizacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where idUsuarioActualizacion not equals to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCReservacionShouldNotBeFound("idUsuarioActualizacion.notEquals=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cReservacionList where idUsuarioActualizacion not equals to UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCReservacionShouldBeFound("idUsuarioActualizacion.notEquals=" + UPDATED_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByIdUsuarioActualizacionIsInShouldWork() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where idUsuarioActualizacion in DEFAULT_ID_USUARIO_ACTUALIZACION or UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCReservacionShouldBeFound("idUsuarioActualizacion.in=" + DEFAULT_ID_USUARIO_ACTUALIZACION + "," + UPDATED_ID_USUARIO_ACTUALIZACION);

        // Get all the cReservacionList where idUsuarioActualizacion equals to UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCReservacionShouldNotBeFound("idUsuarioActualizacion.in=" + UPDATED_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByIdUsuarioActualizacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where idUsuarioActualizacion is not null
        defaultCReservacionShouldBeFound("idUsuarioActualizacion.specified=true");

        // Get all the cReservacionList where idUsuarioActualizacion is null
        defaultCReservacionShouldNotBeFound("idUsuarioActualizacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCReservacionsByIdUsuarioActualizacionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where idUsuarioActualizacion is greater than or equal to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCReservacionShouldBeFound("idUsuarioActualizacion.greaterThanOrEqual=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cReservacionList where idUsuarioActualizacion is greater than or equal to (DEFAULT_ID_USUARIO_ACTUALIZACION + 1)
        defaultCReservacionShouldNotBeFound("idUsuarioActualizacion.greaterThanOrEqual=" + (DEFAULT_ID_USUARIO_ACTUALIZACION + 1));
    }

    @Test
    @Transactional
    public void getAllCReservacionsByIdUsuarioActualizacionIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where idUsuarioActualizacion is less than or equal to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCReservacionShouldBeFound("idUsuarioActualizacion.lessThanOrEqual=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cReservacionList where idUsuarioActualizacion is less than or equal to SMALLER_ID_USUARIO_ACTUALIZACION
        defaultCReservacionShouldNotBeFound("idUsuarioActualizacion.lessThanOrEqual=" + SMALLER_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByIdUsuarioActualizacionIsLessThanSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where idUsuarioActualizacion is less than DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCReservacionShouldNotBeFound("idUsuarioActualizacion.lessThan=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cReservacionList where idUsuarioActualizacion is less than (DEFAULT_ID_USUARIO_ACTUALIZACION + 1)
        defaultCReservacionShouldBeFound("idUsuarioActualizacion.lessThan=" + (DEFAULT_ID_USUARIO_ACTUALIZACION + 1));
    }

    @Test
    @Transactional
    public void getAllCReservacionsByIdUsuarioActualizacionIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where idUsuarioActualizacion is greater than DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCReservacionShouldNotBeFound("idUsuarioActualizacion.greaterThan=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cReservacionList where idUsuarioActualizacion is greater than SMALLER_ID_USUARIO_ACTUALIZACION
        defaultCReservacionShouldBeFound("idUsuarioActualizacion.greaterThan=" + SMALLER_ID_USUARIO_ACTUALIZACION);
    }


    @Test
    @Transactional
    public void getAllCReservacionsByFechaActualizacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where fechaActualizacion equals to DEFAULT_FECHA_ACTUALIZACION
        defaultCReservacionShouldBeFound("fechaActualizacion.equals=" + DEFAULT_FECHA_ACTUALIZACION);

        // Get all the cReservacionList where fechaActualizacion equals to UPDATED_FECHA_ACTUALIZACION
        defaultCReservacionShouldNotBeFound("fechaActualizacion.equals=" + UPDATED_FECHA_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByFechaActualizacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where fechaActualizacion not equals to DEFAULT_FECHA_ACTUALIZACION
        defaultCReservacionShouldNotBeFound("fechaActualizacion.notEquals=" + DEFAULT_FECHA_ACTUALIZACION);

        // Get all the cReservacionList where fechaActualizacion not equals to UPDATED_FECHA_ACTUALIZACION
        defaultCReservacionShouldBeFound("fechaActualizacion.notEquals=" + UPDATED_FECHA_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByFechaActualizacionIsInShouldWork() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where fechaActualizacion in DEFAULT_FECHA_ACTUALIZACION or UPDATED_FECHA_ACTUALIZACION
        defaultCReservacionShouldBeFound("fechaActualizacion.in=" + DEFAULT_FECHA_ACTUALIZACION + "," + UPDATED_FECHA_ACTUALIZACION);

        // Get all the cReservacionList where fechaActualizacion equals to UPDATED_FECHA_ACTUALIZACION
        defaultCReservacionShouldNotBeFound("fechaActualizacion.in=" + UPDATED_FECHA_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByFechaActualizacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where fechaActualizacion is not null
        defaultCReservacionShouldBeFound("fechaActualizacion.specified=true");

        // Get all the cReservacionList where fechaActualizacion is null
        defaultCReservacionShouldNotBeFound("fechaActualizacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCReservacionsByNotasIsEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where notas equals to DEFAULT_NOTAS
        defaultCReservacionShouldBeFound("notas.equals=" + DEFAULT_NOTAS);

        // Get all the cReservacionList where notas equals to UPDATED_NOTAS
        defaultCReservacionShouldNotBeFound("notas.equals=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByNotasIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where notas not equals to DEFAULT_NOTAS
        defaultCReservacionShouldNotBeFound("notas.notEquals=" + DEFAULT_NOTAS);

        // Get all the cReservacionList where notas not equals to UPDATED_NOTAS
        defaultCReservacionShouldBeFound("notas.notEquals=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByNotasIsInShouldWork() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where notas in DEFAULT_NOTAS or UPDATED_NOTAS
        defaultCReservacionShouldBeFound("notas.in=" + DEFAULT_NOTAS + "," + UPDATED_NOTAS);

        // Get all the cReservacionList where notas equals to UPDATED_NOTAS
        defaultCReservacionShouldNotBeFound("notas.in=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByNotasIsNullOrNotNull() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where notas is not null
        defaultCReservacionShouldBeFound("notas.specified=true");

        // Get all the cReservacionList where notas is null
        defaultCReservacionShouldNotBeFound("notas.specified=false");
    }
                @Test
    @Transactional
    public void getAllCReservacionsByNotasContainsSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where notas contains DEFAULT_NOTAS
        defaultCReservacionShouldBeFound("notas.contains=" + DEFAULT_NOTAS);

        // Get all the cReservacionList where notas contains UPDATED_NOTAS
        defaultCReservacionShouldNotBeFound("notas.contains=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByNotasNotContainsSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where notas does not contain DEFAULT_NOTAS
        defaultCReservacionShouldNotBeFound("notas.doesNotContain=" + DEFAULT_NOTAS);

        // Get all the cReservacionList where notas does not contain UPDATED_NOTAS
        defaultCReservacionShouldBeFound("notas.doesNotContain=" + UPDATED_NOTAS);
    }


    @Test
    @Transactional
    public void getAllCReservacionsByEstatusIsEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where estatus equals to DEFAULT_ESTATUS
        defaultCReservacionShouldBeFound("estatus.equals=" + DEFAULT_ESTATUS);

        // Get all the cReservacionList where estatus equals to UPDATED_ESTATUS
        defaultCReservacionShouldNotBeFound("estatus.equals=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByEstatusIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where estatus not equals to DEFAULT_ESTATUS
        defaultCReservacionShouldNotBeFound("estatus.notEquals=" + DEFAULT_ESTATUS);

        // Get all the cReservacionList where estatus not equals to UPDATED_ESTATUS
        defaultCReservacionShouldBeFound("estatus.notEquals=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByEstatusIsInShouldWork() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where estatus in DEFAULT_ESTATUS or UPDATED_ESTATUS
        defaultCReservacionShouldBeFound("estatus.in=" + DEFAULT_ESTATUS + "," + UPDATED_ESTATUS);

        // Get all the cReservacionList where estatus equals to UPDATED_ESTATUS
        defaultCReservacionShouldNotBeFound("estatus.in=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByEstatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where estatus is not null
        defaultCReservacionShouldBeFound("estatus.specified=true");

        // Get all the cReservacionList where estatus is null
        defaultCReservacionShouldNotBeFound("estatus.specified=false");
    }

    @Test
    @Transactional
    public void getAllCReservacionsByEstatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where estatus is greater than or equal to DEFAULT_ESTATUS
        defaultCReservacionShouldBeFound("estatus.greaterThanOrEqual=" + DEFAULT_ESTATUS);

        // Get all the cReservacionList where estatus is greater than or equal to (DEFAULT_ESTATUS + 1)
        defaultCReservacionShouldNotBeFound("estatus.greaterThanOrEqual=" + (DEFAULT_ESTATUS + 1));
    }

    @Test
    @Transactional
    public void getAllCReservacionsByEstatusIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where estatus is less than or equal to DEFAULT_ESTATUS
        defaultCReservacionShouldBeFound("estatus.lessThanOrEqual=" + DEFAULT_ESTATUS);

        // Get all the cReservacionList where estatus is less than or equal to SMALLER_ESTATUS
        defaultCReservacionShouldNotBeFound("estatus.lessThanOrEqual=" + SMALLER_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByEstatusIsLessThanSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where estatus is less than DEFAULT_ESTATUS
        defaultCReservacionShouldNotBeFound("estatus.lessThan=" + DEFAULT_ESTATUS);

        // Get all the cReservacionList where estatus is less than (DEFAULT_ESTATUS + 1)
        defaultCReservacionShouldBeFound("estatus.lessThan=" + (DEFAULT_ESTATUS + 1));
    }

    @Test
    @Transactional
    public void getAllCReservacionsByEstatusIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where estatus is greater than DEFAULT_ESTATUS
        defaultCReservacionShouldNotBeFound("estatus.greaterThan=" + DEFAULT_ESTATUS);

        // Get all the cReservacionList where estatus is greater than SMALLER_ESTATUS
        defaultCReservacionShouldBeFound("estatus.greaterThan=" + SMALLER_ESTATUS);
    }


    @Test
    @Transactional
    public void getAllCReservacionsByBorradoIsEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where borrado equals to DEFAULT_BORRADO
        defaultCReservacionShouldBeFound("borrado.equals=" + DEFAULT_BORRADO);

        // Get all the cReservacionList where borrado equals to UPDATED_BORRADO
        defaultCReservacionShouldNotBeFound("borrado.equals=" + UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByBorradoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where borrado not equals to DEFAULT_BORRADO
        defaultCReservacionShouldNotBeFound("borrado.notEquals=" + DEFAULT_BORRADO);

        // Get all the cReservacionList where borrado not equals to UPDATED_BORRADO
        defaultCReservacionShouldBeFound("borrado.notEquals=" + UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByBorradoIsInShouldWork() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where borrado in DEFAULT_BORRADO or UPDATED_BORRADO
        defaultCReservacionShouldBeFound("borrado.in=" + DEFAULT_BORRADO + "," + UPDATED_BORRADO);

        // Get all the cReservacionList where borrado equals to UPDATED_BORRADO
        defaultCReservacionShouldNotBeFound("borrado.in=" + UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByBorradoIsNullOrNotNull() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where borrado is not null
        defaultCReservacionShouldBeFound("borrado.specified=true");

        // Get all the cReservacionList where borrado is null
        defaultCReservacionShouldNotBeFound("borrado.specified=false");
    }

    @Test
    @Transactional
    public void getAllCReservacionsByBorradoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where borrado is greater than or equal to DEFAULT_BORRADO
        defaultCReservacionShouldBeFound("borrado.greaterThanOrEqual=" + DEFAULT_BORRADO);

        // Get all the cReservacionList where borrado is greater than or equal to (DEFAULT_BORRADO + 1)
        defaultCReservacionShouldNotBeFound("borrado.greaterThanOrEqual=" + (DEFAULT_BORRADO + 1));
    }

    @Test
    @Transactional
    public void getAllCReservacionsByBorradoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where borrado is less than or equal to DEFAULT_BORRADO
        defaultCReservacionShouldBeFound("borrado.lessThanOrEqual=" + DEFAULT_BORRADO);

        // Get all the cReservacionList where borrado is less than or equal to SMALLER_BORRADO
        defaultCReservacionShouldNotBeFound("borrado.lessThanOrEqual=" + SMALLER_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCReservacionsByBorradoIsLessThanSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where borrado is less than DEFAULT_BORRADO
        defaultCReservacionShouldNotBeFound("borrado.lessThan=" + DEFAULT_BORRADO);

        // Get all the cReservacionList where borrado is less than (DEFAULT_BORRADO + 1)
        defaultCReservacionShouldBeFound("borrado.lessThan=" + (DEFAULT_BORRADO + 1));
    }

    @Test
    @Transactional
    public void getAllCReservacionsByBorradoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        // Get all the cReservacionList where borrado is greater than DEFAULT_BORRADO
        defaultCReservacionShouldNotBeFound("borrado.greaterThan=" + DEFAULT_BORRADO);

        // Get all the cReservacionList where borrado is greater than SMALLER_BORRADO
        defaultCReservacionShouldBeFound("borrado.greaterThan=" + SMALLER_BORRADO);
    }


    @Test
    @Transactional
    public void getAllCReservacionsByClienteIsEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);
        CCliente cliente = CClienteResourceIT.createEntity(em);
        em.persist(cliente);
        em.flush();
        cReservacion.setCliente(cliente);
        cReservacionRepository.saveAndFlush(cReservacion);
        Long clienteId = cliente.getId();

        // Get all the cReservacionList where cliente equals to clienteId
        defaultCReservacionShouldBeFound("clienteId.equals=" + clienteId);

        // Get all the cReservacionList where cliente equals to clienteId + 1
        defaultCReservacionShouldNotBeFound("clienteId.equals=" + (clienteId + 1));
    }


    @Test
    @Transactional
    public void getAllCReservacionsByPasajeroPrincipalIsEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);
        CPasajero pasajeroPrincipal = CPasajeroResourceIT.createEntity(em);
        em.persist(pasajeroPrincipal);
        em.flush();
        cReservacion.setPasajeroPrincipal(pasajeroPrincipal);
        cReservacionRepository.saveAndFlush(cReservacion);
        Long pasajeroPrincipalId = pasajeroPrincipal.getId();

        // Get all the cReservacionList where pasajeroPrincipal equals to pasajeroPrincipalId
        defaultCReservacionShouldBeFound("pasajeroPrincipalId.equals=" + pasajeroPrincipalId);

        // Get all the cReservacionList where pasajeroPrincipal equals to pasajeroPrincipalId + 1
        defaultCReservacionShouldNotBeFound("pasajeroPrincipalId.equals=" + (pasajeroPrincipalId + 1));
    }


    @Test
    @Transactional
    public void getAllCReservacionsByCorridaIsEqualToSomething() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);
        CCorrida corrida = CCorridaResourceIT.createEntity(em);
        em.persist(corrida);
        em.flush();
        cReservacion.setCorrida(corrida);
        cReservacionRepository.saveAndFlush(cReservacion);
        Long corridaId = corrida.getId();

        // Get all the cReservacionList where corrida equals to corridaId
        defaultCReservacionShouldBeFound("corridaId.equals=" + corridaId);

        // Get all the cReservacionList where corrida equals to corridaId + 1
        defaultCReservacionShouldNotBeFound("corridaId.equals=" + (corridaId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCReservacionShouldBeFound(String filter) throws Exception {
        restCReservacionMockMvc.perform(get("/api/c-reservacions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cReservacion.getId().intValue())))
            .andExpect(jsonPath("$.[*].claveReservacion").value(hasItem(DEFAULT_CLAVE_RESERVACION)))
            .andExpect(jsonPath("$.[*].precio").value(hasItem(DEFAULT_PRECIO.doubleValue())))
            .andExpect(jsonPath("$.[*].numPasajeros").value(hasItem(DEFAULT_NUM_PASAJEROS)))
            .andExpect(jsonPath("$.[*].comentarios").value(hasItem(DEFAULT_COMENTARIOS)))
            .andExpect(jsonPath("$.[*].idUsuarioCreacion").value(hasItem(DEFAULT_ID_USUARIO_CREACION.intValue())))
            .andExpect(jsonPath("$.[*].fechaCreacion").value(hasItem(DEFAULT_FECHA_CREACION.toString())))
            .andExpect(jsonPath("$.[*].idUsuarioActualizacion").value(hasItem(DEFAULT_ID_USUARIO_ACTUALIZACION.intValue())))
            .andExpect(jsonPath("$.[*].fechaActualizacion").value(hasItem(DEFAULT_FECHA_ACTUALIZACION.toString())))
            .andExpect(jsonPath("$.[*].notas").value(hasItem(DEFAULT_NOTAS)))
            .andExpect(jsonPath("$.[*].estatus").value(hasItem(DEFAULT_ESTATUS)))
            .andExpect(jsonPath("$.[*].borrado").value(hasItem(DEFAULT_BORRADO)));

        // Check, that the count call also returns 1
        restCReservacionMockMvc.perform(get("/api/c-reservacions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCReservacionShouldNotBeFound(String filter) throws Exception {
        restCReservacionMockMvc.perform(get("/api/c-reservacions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCReservacionMockMvc.perform(get("/api/c-reservacions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCReservacion() throws Exception {
        // Get the cReservacion
        restCReservacionMockMvc.perform(get("/api/c-reservacions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCReservacion() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        int databaseSizeBeforeUpdate = cReservacionRepository.findAll().size();

        // Update the cReservacion
        CReservacion updatedCReservacion = cReservacionRepository.findById(cReservacion.getId()).get();
        // Disconnect from session so that the updates on updatedCReservacion are not directly saved in db
        em.detach(updatedCReservacion);
        updatedCReservacion
            .claveReservacion(UPDATED_CLAVE_RESERVACION)
            .precio(UPDATED_PRECIO)
            .numPasajeros(UPDATED_NUM_PASAJEROS)
            .comentarios(UPDATED_COMENTARIOS)
            .idUsuarioCreacion(UPDATED_ID_USUARIO_CREACION)
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .idUsuarioActualizacion(UPDATED_ID_USUARIO_ACTUALIZACION)
            .fechaActualizacion(UPDATED_FECHA_ACTUALIZACION)
            .notas(UPDATED_NOTAS)
            .estatus(UPDATED_ESTATUS)
            .borrado(UPDATED_BORRADO);
        CReservacionDTO cReservacionDTO = cReservacionMapper.toDto(updatedCReservacion);

        restCReservacionMockMvc.perform(put("/api/c-reservacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cReservacionDTO)))
            .andExpect(status().isOk());

        // Validate the CReservacion in the database
        List<CReservacion> cReservacionList = cReservacionRepository.findAll();
        assertThat(cReservacionList).hasSize(databaseSizeBeforeUpdate);
        CReservacion testCReservacion = cReservacionList.get(cReservacionList.size() - 1);
        assertThat(testCReservacion.getClaveReservacion()).isEqualTo(UPDATED_CLAVE_RESERVACION);
        assertThat(testCReservacion.getPrecio()).isEqualTo(UPDATED_PRECIO);
        assertThat(testCReservacion.getNumPasajeros()).isEqualTo(UPDATED_NUM_PASAJEROS);
        assertThat(testCReservacion.getComentarios()).isEqualTo(UPDATED_COMENTARIOS);
        assertThat(testCReservacion.getIdUsuarioCreacion()).isEqualTo(UPDATED_ID_USUARIO_CREACION);
        assertThat(testCReservacion.getFechaCreacion()).isEqualTo(UPDATED_FECHA_CREACION);
        assertThat(testCReservacion.getIdUsuarioActualizacion()).isEqualTo(UPDATED_ID_USUARIO_ACTUALIZACION);
        assertThat(testCReservacion.getFechaActualizacion()).isEqualTo(UPDATED_FECHA_ACTUALIZACION);
        assertThat(testCReservacion.getNotas()).isEqualTo(UPDATED_NOTAS);
        assertThat(testCReservacion.getEstatus()).isEqualTo(UPDATED_ESTATUS);
        assertThat(testCReservacion.getBorrado()).isEqualTo(UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void updateNonExistingCReservacion() throws Exception {
        int databaseSizeBeforeUpdate = cReservacionRepository.findAll().size();

        // Create the CReservacion
        CReservacionDTO cReservacionDTO = cReservacionMapper.toDto(cReservacion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCReservacionMockMvc.perform(put("/api/c-reservacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cReservacionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CReservacion in the database
        List<CReservacion> cReservacionList = cReservacionRepository.findAll();
        assertThat(cReservacionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCReservacion() throws Exception {
        // Initialize the database
        cReservacionRepository.saveAndFlush(cReservacion);

        int databaseSizeBeforeDelete = cReservacionRepository.findAll().size();

        // Delete the cReservacion
        restCReservacionMockMvc.perform(delete("/api/c-reservacions/{id}", cReservacion.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CReservacion> cReservacionList = cReservacionRepository.findAll();
        assertThat(cReservacionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
