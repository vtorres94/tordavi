package mx.com.tordavi.web.rest;

import mx.com.tordavi.TordaviApp;
import mx.com.tordavi.domain.CDetalleReservacion;
import mx.com.tordavi.domain.CCliente;
import mx.com.tordavi.domain.CPasajero;
import mx.com.tordavi.domain.CReservacion;
import mx.com.tordavi.repository.CDetalleReservacionRepository;
import mx.com.tordavi.service.CDetalleReservacionService;
import mx.com.tordavi.service.dto.CDetalleReservacionDTO;
import mx.com.tordavi.service.mapper.CDetalleReservacionMapper;
import mx.com.tordavi.service.dto.CDetalleReservacionCriteria;
import mx.com.tordavi.service.CDetalleReservacionQueryService;

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
 * Integration tests for the {@link CDetalleReservacionResource} REST controller.
 */
@SpringBootTest(classes = TordaviApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class CDetalleReservacionResourceIT {

    private static final Double DEFAULT_PRECIO = 1D;
    private static final Double UPDATED_PRECIO = 2D;
    private static final Double SMALLER_PRECIO = 1D - 1D;

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
    private CDetalleReservacionRepository cDetalleReservacionRepository;

    @Autowired
    private CDetalleReservacionMapper cDetalleReservacionMapper;

    @Autowired
    private CDetalleReservacionService cDetalleReservacionService;

    @Autowired
    private CDetalleReservacionQueryService cDetalleReservacionQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCDetalleReservacionMockMvc;

    private CDetalleReservacion cDetalleReservacion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CDetalleReservacion createEntity(EntityManager em) {
        CDetalleReservacion cDetalleReservacion = new CDetalleReservacion()
            .precio(DEFAULT_PRECIO)
            .idUsuarioCreacion(DEFAULT_ID_USUARIO_CREACION)
            .fechaCreacion(DEFAULT_FECHA_CREACION)
            .idUsuarioActualizacion(DEFAULT_ID_USUARIO_ACTUALIZACION)
            .fechaActualizacion(DEFAULT_FECHA_ACTUALIZACION)
            .notas(DEFAULT_NOTAS)
            .estatus(DEFAULT_ESTATUS)
            .borrado(DEFAULT_BORRADO);
        return cDetalleReservacion;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CDetalleReservacion createUpdatedEntity(EntityManager em) {
        CDetalleReservacion cDetalleReservacion = new CDetalleReservacion()
            .precio(UPDATED_PRECIO)
            .idUsuarioCreacion(UPDATED_ID_USUARIO_CREACION)
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .idUsuarioActualizacion(UPDATED_ID_USUARIO_ACTUALIZACION)
            .fechaActualizacion(UPDATED_FECHA_ACTUALIZACION)
            .notas(UPDATED_NOTAS)
            .estatus(UPDATED_ESTATUS)
            .borrado(UPDATED_BORRADO);
        return cDetalleReservacion;
    }

    @BeforeEach
    public void initTest() {
        cDetalleReservacion = createEntity(em);
    }

    @Test
    @Transactional
    public void createCDetalleReservacion() throws Exception {
        int databaseSizeBeforeCreate = cDetalleReservacionRepository.findAll().size();

        // Create the CDetalleReservacion
        CDetalleReservacionDTO cDetalleReservacionDTO = cDetalleReservacionMapper.toDto(cDetalleReservacion);
        restCDetalleReservacionMockMvc.perform(post("/api/c-detalle-reservacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cDetalleReservacionDTO)))
            .andExpect(status().isCreated());

        // Validate the CDetalleReservacion in the database
        List<CDetalleReservacion> cDetalleReservacionList = cDetalleReservacionRepository.findAll();
        assertThat(cDetalleReservacionList).hasSize(databaseSizeBeforeCreate + 1);
        CDetalleReservacion testCDetalleReservacion = cDetalleReservacionList.get(cDetalleReservacionList.size() - 1);
        assertThat(testCDetalleReservacion.getPrecio()).isEqualTo(DEFAULT_PRECIO);
        assertThat(testCDetalleReservacion.getIdUsuarioCreacion()).isEqualTo(DEFAULT_ID_USUARIO_CREACION);
        assertThat(testCDetalleReservacion.getFechaCreacion()).isEqualTo(DEFAULT_FECHA_CREACION);
        assertThat(testCDetalleReservacion.getIdUsuarioActualizacion()).isEqualTo(DEFAULT_ID_USUARIO_ACTUALIZACION);
        assertThat(testCDetalleReservacion.getFechaActualizacion()).isEqualTo(DEFAULT_FECHA_ACTUALIZACION);
        assertThat(testCDetalleReservacion.getNotas()).isEqualTo(DEFAULT_NOTAS);
        assertThat(testCDetalleReservacion.getEstatus()).isEqualTo(DEFAULT_ESTATUS);
        assertThat(testCDetalleReservacion.getBorrado()).isEqualTo(DEFAULT_BORRADO);
    }

    @Test
    @Transactional
    public void createCDetalleReservacionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cDetalleReservacionRepository.findAll().size();

        // Create the CDetalleReservacion with an existing ID
        cDetalleReservacion.setId(1L);
        CDetalleReservacionDTO cDetalleReservacionDTO = cDetalleReservacionMapper.toDto(cDetalleReservacion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCDetalleReservacionMockMvc.perform(post("/api/c-detalle-reservacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cDetalleReservacionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CDetalleReservacion in the database
        List<CDetalleReservacion> cDetalleReservacionList = cDetalleReservacionRepository.findAll();
        assertThat(cDetalleReservacionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdUsuarioCreacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = cDetalleReservacionRepository.findAll().size();
        // set the field null
        cDetalleReservacion.setIdUsuarioCreacion(null);

        // Create the CDetalleReservacion, which fails.
        CDetalleReservacionDTO cDetalleReservacionDTO = cDetalleReservacionMapper.toDto(cDetalleReservacion);

        restCDetalleReservacionMockMvc.perform(post("/api/c-detalle-reservacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cDetalleReservacionDTO)))
            .andExpect(status().isBadRequest());

        List<CDetalleReservacion> cDetalleReservacionList = cDetalleReservacionRepository.findAll();
        assertThat(cDetalleReservacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFechaCreacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = cDetalleReservacionRepository.findAll().size();
        // set the field null
        cDetalleReservacion.setFechaCreacion(null);

        // Create the CDetalleReservacion, which fails.
        CDetalleReservacionDTO cDetalleReservacionDTO = cDetalleReservacionMapper.toDto(cDetalleReservacion);

        restCDetalleReservacionMockMvc.perform(post("/api/c-detalle-reservacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cDetalleReservacionDTO)))
            .andExpect(status().isBadRequest());

        List<CDetalleReservacion> cDetalleReservacionList = cDetalleReservacionRepository.findAll();
        assertThat(cDetalleReservacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = cDetalleReservacionRepository.findAll().size();
        // set the field null
        cDetalleReservacion.setEstatus(null);

        // Create the CDetalleReservacion, which fails.
        CDetalleReservacionDTO cDetalleReservacionDTO = cDetalleReservacionMapper.toDto(cDetalleReservacion);

        restCDetalleReservacionMockMvc.perform(post("/api/c-detalle-reservacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cDetalleReservacionDTO)))
            .andExpect(status().isBadRequest());

        List<CDetalleReservacion> cDetalleReservacionList = cDetalleReservacionRepository.findAll();
        assertThat(cDetalleReservacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBorradoIsRequired() throws Exception {
        int databaseSizeBeforeTest = cDetalleReservacionRepository.findAll().size();
        // set the field null
        cDetalleReservacion.setBorrado(null);

        // Create the CDetalleReservacion, which fails.
        CDetalleReservacionDTO cDetalleReservacionDTO = cDetalleReservacionMapper.toDto(cDetalleReservacion);

        restCDetalleReservacionMockMvc.perform(post("/api/c-detalle-reservacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cDetalleReservacionDTO)))
            .andExpect(status().isBadRequest());

        List<CDetalleReservacion> cDetalleReservacionList = cDetalleReservacionRepository.findAll();
        assertThat(cDetalleReservacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacions() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList
        restCDetalleReservacionMockMvc.perform(get("/api/c-detalle-reservacions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cDetalleReservacion.getId().intValue())))
            .andExpect(jsonPath("$.[*].precio").value(hasItem(DEFAULT_PRECIO.doubleValue())))
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
    public void getCDetalleReservacion() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get the cDetalleReservacion
        restCDetalleReservacionMockMvc.perform(get("/api/c-detalle-reservacions/{id}", cDetalleReservacion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cDetalleReservacion.getId().intValue()))
            .andExpect(jsonPath("$.precio").value(DEFAULT_PRECIO.doubleValue()))
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
    public void getCDetalleReservacionsByIdFiltering() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        Long id = cDetalleReservacion.getId();

        defaultCDetalleReservacionShouldBeFound("id.equals=" + id);
        defaultCDetalleReservacionShouldNotBeFound("id.notEquals=" + id);

        defaultCDetalleReservacionShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCDetalleReservacionShouldNotBeFound("id.greaterThan=" + id);

        defaultCDetalleReservacionShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCDetalleReservacionShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllCDetalleReservacionsByPrecioIsEqualToSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where precio equals to DEFAULT_PRECIO
        defaultCDetalleReservacionShouldBeFound("precio.equals=" + DEFAULT_PRECIO);

        // Get all the cDetalleReservacionList where precio equals to UPDATED_PRECIO
        defaultCDetalleReservacionShouldNotBeFound("precio.equals=" + UPDATED_PRECIO);
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByPrecioIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where precio not equals to DEFAULT_PRECIO
        defaultCDetalleReservacionShouldNotBeFound("precio.notEquals=" + DEFAULT_PRECIO);

        // Get all the cDetalleReservacionList where precio not equals to UPDATED_PRECIO
        defaultCDetalleReservacionShouldBeFound("precio.notEquals=" + UPDATED_PRECIO);
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByPrecioIsInShouldWork() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where precio in DEFAULT_PRECIO or UPDATED_PRECIO
        defaultCDetalleReservacionShouldBeFound("precio.in=" + DEFAULT_PRECIO + "," + UPDATED_PRECIO);

        // Get all the cDetalleReservacionList where precio equals to UPDATED_PRECIO
        defaultCDetalleReservacionShouldNotBeFound("precio.in=" + UPDATED_PRECIO);
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByPrecioIsNullOrNotNull() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where precio is not null
        defaultCDetalleReservacionShouldBeFound("precio.specified=true");

        // Get all the cDetalleReservacionList where precio is null
        defaultCDetalleReservacionShouldNotBeFound("precio.specified=false");
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByPrecioIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where precio is greater than or equal to DEFAULT_PRECIO
        defaultCDetalleReservacionShouldBeFound("precio.greaterThanOrEqual=" + DEFAULT_PRECIO);

        // Get all the cDetalleReservacionList where precio is greater than or equal to UPDATED_PRECIO
        defaultCDetalleReservacionShouldNotBeFound("precio.greaterThanOrEqual=" + UPDATED_PRECIO);
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByPrecioIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where precio is less than or equal to DEFAULT_PRECIO
        defaultCDetalleReservacionShouldBeFound("precio.lessThanOrEqual=" + DEFAULT_PRECIO);

        // Get all the cDetalleReservacionList where precio is less than or equal to SMALLER_PRECIO
        defaultCDetalleReservacionShouldNotBeFound("precio.lessThanOrEqual=" + SMALLER_PRECIO);
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByPrecioIsLessThanSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where precio is less than DEFAULT_PRECIO
        defaultCDetalleReservacionShouldNotBeFound("precio.lessThan=" + DEFAULT_PRECIO);

        // Get all the cDetalleReservacionList where precio is less than UPDATED_PRECIO
        defaultCDetalleReservacionShouldBeFound("precio.lessThan=" + UPDATED_PRECIO);
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByPrecioIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where precio is greater than DEFAULT_PRECIO
        defaultCDetalleReservacionShouldNotBeFound("precio.greaterThan=" + DEFAULT_PRECIO);

        // Get all the cDetalleReservacionList where precio is greater than SMALLER_PRECIO
        defaultCDetalleReservacionShouldBeFound("precio.greaterThan=" + SMALLER_PRECIO);
    }


    @Test
    @Transactional
    public void getAllCDetalleReservacionsByIdUsuarioCreacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where idUsuarioCreacion equals to DEFAULT_ID_USUARIO_CREACION
        defaultCDetalleReservacionShouldBeFound("idUsuarioCreacion.equals=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cDetalleReservacionList where idUsuarioCreacion equals to UPDATED_ID_USUARIO_CREACION
        defaultCDetalleReservacionShouldNotBeFound("idUsuarioCreacion.equals=" + UPDATED_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByIdUsuarioCreacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where idUsuarioCreacion not equals to DEFAULT_ID_USUARIO_CREACION
        defaultCDetalleReservacionShouldNotBeFound("idUsuarioCreacion.notEquals=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cDetalleReservacionList where idUsuarioCreacion not equals to UPDATED_ID_USUARIO_CREACION
        defaultCDetalleReservacionShouldBeFound("idUsuarioCreacion.notEquals=" + UPDATED_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByIdUsuarioCreacionIsInShouldWork() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where idUsuarioCreacion in DEFAULT_ID_USUARIO_CREACION or UPDATED_ID_USUARIO_CREACION
        defaultCDetalleReservacionShouldBeFound("idUsuarioCreacion.in=" + DEFAULT_ID_USUARIO_CREACION + "," + UPDATED_ID_USUARIO_CREACION);

        // Get all the cDetalleReservacionList where idUsuarioCreacion equals to UPDATED_ID_USUARIO_CREACION
        defaultCDetalleReservacionShouldNotBeFound("idUsuarioCreacion.in=" + UPDATED_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByIdUsuarioCreacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where idUsuarioCreacion is not null
        defaultCDetalleReservacionShouldBeFound("idUsuarioCreacion.specified=true");

        // Get all the cDetalleReservacionList where idUsuarioCreacion is null
        defaultCDetalleReservacionShouldNotBeFound("idUsuarioCreacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByIdUsuarioCreacionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where idUsuarioCreacion is greater than or equal to DEFAULT_ID_USUARIO_CREACION
        defaultCDetalleReservacionShouldBeFound("idUsuarioCreacion.greaterThanOrEqual=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cDetalleReservacionList where idUsuarioCreacion is greater than or equal to (DEFAULT_ID_USUARIO_CREACION + 1)
        defaultCDetalleReservacionShouldNotBeFound("idUsuarioCreacion.greaterThanOrEqual=" + (DEFAULT_ID_USUARIO_CREACION + 1));
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByIdUsuarioCreacionIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where idUsuarioCreacion is less than or equal to DEFAULT_ID_USUARIO_CREACION
        defaultCDetalleReservacionShouldBeFound("idUsuarioCreacion.lessThanOrEqual=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cDetalleReservacionList where idUsuarioCreacion is less than or equal to SMALLER_ID_USUARIO_CREACION
        defaultCDetalleReservacionShouldNotBeFound("idUsuarioCreacion.lessThanOrEqual=" + SMALLER_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByIdUsuarioCreacionIsLessThanSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where idUsuarioCreacion is less than DEFAULT_ID_USUARIO_CREACION
        defaultCDetalleReservacionShouldNotBeFound("idUsuarioCreacion.lessThan=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cDetalleReservacionList where idUsuarioCreacion is less than (DEFAULT_ID_USUARIO_CREACION + 1)
        defaultCDetalleReservacionShouldBeFound("idUsuarioCreacion.lessThan=" + (DEFAULT_ID_USUARIO_CREACION + 1));
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByIdUsuarioCreacionIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where idUsuarioCreacion is greater than DEFAULT_ID_USUARIO_CREACION
        defaultCDetalleReservacionShouldNotBeFound("idUsuarioCreacion.greaterThan=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cDetalleReservacionList where idUsuarioCreacion is greater than SMALLER_ID_USUARIO_CREACION
        defaultCDetalleReservacionShouldBeFound("idUsuarioCreacion.greaterThan=" + SMALLER_ID_USUARIO_CREACION);
    }


    @Test
    @Transactional
    public void getAllCDetalleReservacionsByFechaCreacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where fechaCreacion equals to DEFAULT_FECHA_CREACION
        defaultCDetalleReservacionShouldBeFound("fechaCreacion.equals=" + DEFAULT_FECHA_CREACION);

        // Get all the cDetalleReservacionList where fechaCreacion equals to UPDATED_FECHA_CREACION
        defaultCDetalleReservacionShouldNotBeFound("fechaCreacion.equals=" + UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByFechaCreacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where fechaCreacion not equals to DEFAULT_FECHA_CREACION
        defaultCDetalleReservacionShouldNotBeFound("fechaCreacion.notEquals=" + DEFAULT_FECHA_CREACION);

        // Get all the cDetalleReservacionList where fechaCreacion not equals to UPDATED_FECHA_CREACION
        defaultCDetalleReservacionShouldBeFound("fechaCreacion.notEquals=" + UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByFechaCreacionIsInShouldWork() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where fechaCreacion in DEFAULT_FECHA_CREACION or UPDATED_FECHA_CREACION
        defaultCDetalleReservacionShouldBeFound("fechaCreacion.in=" + DEFAULT_FECHA_CREACION + "," + UPDATED_FECHA_CREACION);

        // Get all the cDetalleReservacionList where fechaCreacion equals to UPDATED_FECHA_CREACION
        defaultCDetalleReservacionShouldNotBeFound("fechaCreacion.in=" + UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByFechaCreacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where fechaCreacion is not null
        defaultCDetalleReservacionShouldBeFound("fechaCreacion.specified=true");

        // Get all the cDetalleReservacionList where fechaCreacion is null
        defaultCDetalleReservacionShouldNotBeFound("fechaCreacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByIdUsuarioActualizacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where idUsuarioActualizacion equals to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCDetalleReservacionShouldBeFound("idUsuarioActualizacion.equals=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cDetalleReservacionList where idUsuarioActualizacion equals to UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCDetalleReservacionShouldNotBeFound("idUsuarioActualizacion.equals=" + UPDATED_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByIdUsuarioActualizacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where idUsuarioActualizacion not equals to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCDetalleReservacionShouldNotBeFound("idUsuarioActualizacion.notEquals=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cDetalleReservacionList where idUsuarioActualizacion not equals to UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCDetalleReservacionShouldBeFound("idUsuarioActualizacion.notEquals=" + UPDATED_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByIdUsuarioActualizacionIsInShouldWork() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where idUsuarioActualizacion in DEFAULT_ID_USUARIO_ACTUALIZACION or UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCDetalleReservacionShouldBeFound("idUsuarioActualizacion.in=" + DEFAULT_ID_USUARIO_ACTUALIZACION + "," + UPDATED_ID_USUARIO_ACTUALIZACION);

        // Get all the cDetalleReservacionList where idUsuarioActualizacion equals to UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCDetalleReservacionShouldNotBeFound("idUsuarioActualizacion.in=" + UPDATED_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByIdUsuarioActualizacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where idUsuarioActualizacion is not null
        defaultCDetalleReservacionShouldBeFound("idUsuarioActualizacion.specified=true");

        // Get all the cDetalleReservacionList where idUsuarioActualizacion is null
        defaultCDetalleReservacionShouldNotBeFound("idUsuarioActualizacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByIdUsuarioActualizacionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where idUsuarioActualizacion is greater than or equal to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCDetalleReservacionShouldBeFound("idUsuarioActualizacion.greaterThanOrEqual=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cDetalleReservacionList where idUsuarioActualizacion is greater than or equal to (DEFAULT_ID_USUARIO_ACTUALIZACION + 1)
        defaultCDetalleReservacionShouldNotBeFound("idUsuarioActualizacion.greaterThanOrEqual=" + (DEFAULT_ID_USUARIO_ACTUALIZACION + 1));
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByIdUsuarioActualizacionIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where idUsuarioActualizacion is less than or equal to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCDetalleReservacionShouldBeFound("idUsuarioActualizacion.lessThanOrEqual=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cDetalleReservacionList where idUsuarioActualizacion is less than or equal to SMALLER_ID_USUARIO_ACTUALIZACION
        defaultCDetalleReservacionShouldNotBeFound("idUsuarioActualizacion.lessThanOrEqual=" + SMALLER_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByIdUsuarioActualizacionIsLessThanSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where idUsuarioActualizacion is less than DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCDetalleReservacionShouldNotBeFound("idUsuarioActualizacion.lessThan=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cDetalleReservacionList where idUsuarioActualizacion is less than (DEFAULT_ID_USUARIO_ACTUALIZACION + 1)
        defaultCDetalleReservacionShouldBeFound("idUsuarioActualizacion.lessThan=" + (DEFAULT_ID_USUARIO_ACTUALIZACION + 1));
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByIdUsuarioActualizacionIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where idUsuarioActualizacion is greater than DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCDetalleReservacionShouldNotBeFound("idUsuarioActualizacion.greaterThan=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cDetalleReservacionList where idUsuarioActualizacion is greater than SMALLER_ID_USUARIO_ACTUALIZACION
        defaultCDetalleReservacionShouldBeFound("idUsuarioActualizacion.greaterThan=" + SMALLER_ID_USUARIO_ACTUALIZACION);
    }


    @Test
    @Transactional
    public void getAllCDetalleReservacionsByFechaActualizacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where fechaActualizacion equals to DEFAULT_FECHA_ACTUALIZACION
        defaultCDetalleReservacionShouldBeFound("fechaActualizacion.equals=" + DEFAULT_FECHA_ACTUALIZACION);

        // Get all the cDetalleReservacionList where fechaActualizacion equals to UPDATED_FECHA_ACTUALIZACION
        defaultCDetalleReservacionShouldNotBeFound("fechaActualizacion.equals=" + UPDATED_FECHA_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByFechaActualizacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where fechaActualizacion not equals to DEFAULT_FECHA_ACTUALIZACION
        defaultCDetalleReservacionShouldNotBeFound("fechaActualizacion.notEquals=" + DEFAULT_FECHA_ACTUALIZACION);

        // Get all the cDetalleReservacionList where fechaActualizacion not equals to UPDATED_FECHA_ACTUALIZACION
        defaultCDetalleReservacionShouldBeFound("fechaActualizacion.notEquals=" + UPDATED_FECHA_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByFechaActualizacionIsInShouldWork() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where fechaActualizacion in DEFAULT_FECHA_ACTUALIZACION or UPDATED_FECHA_ACTUALIZACION
        defaultCDetalleReservacionShouldBeFound("fechaActualizacion.in=" + DEFAULT_FECHA_ACTUALIZACION + "," + UPDATED_FECHA_ACTUALIZACION);

        // Get all the cDetalleReservacionList where fechaActualizacion equals to UPDATED_FECHA_ACTUALIZACION
        defaultCDetalleReservacionShouldNotBeFound("fechaActualizacion.in=" + UPDATED_FECHA_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByFechaActualizacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where fechaActualizacion is not null
        defaultCDetalleReservacionShouldBeFound("fechaActualizacion.specified=true");

        // Get all the cDetalleReservacionList where fechaActualizacion is null
        defaultCDetalleReservacionShouldNotBeFound("fechaActualizacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByNotasIsEqualToSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where notas equals to DEFAULT_NOTAS
        defaultCDetalleReservacionShouldBeFound("notas.equals=" + DEFAULT_NOTAS);

        // Get all the cDetalleReservacionList where notas equals to UPDATED_NOTAS
        defaultCDetalleReservacionShouldNotBeFound("notas.equals=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByNotasIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where notas not equals to DEFAULT_NOTAS
        defaultCDetalleReservacionShouldNotBeFound("notas.notEquals=" + DEFAULT_NOTAS);

        // Get all the cDetalleReservacionList where notas not equals to UPDATED_NOTAS
        defaultCDetalleReservacionShouldBeFound("notas.notEquals=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByNotasIsInShouldWork() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where notas in DEFAULT_NOTAS or UPDATED_NOTAS
        defaultCDetalleReservacionShouldBeFound("notas.in=" + DEFAULT_NOTAS + "," + UPDATED_NOTAS);

        // Get all the cDetalleReservacionList where notas equals to UPDATED_NOTAS
        defaultCDetalleReservacionShouldNotBeFound("notas.in=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByNotasIsNullOrNotNull() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where notas is not null
        defaultCDetalleReservacionShouldBeFound("notas.specified=true");

        // Get all the cDetalleReservacionList where notas is null
        defaultCDetalleReservacionShouldNotBeFound("notas.specified=false");
    }
                @Test
    @Transactional
    public void getAllCDetalleReservacionsByNotasContainsSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where notas contains DEFAULT_NOTAS
        defaultCDetalleReservacionShouldBeFound("notas.contains=" + DEFAULT_NOTAS);

        // Get all the cDetalleReservacionList where notas contains UPDATED_NOTAS
        defaultCDetalleReservacionShouldNotBeFound("notas.contains=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByNotasNotContainsSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where notas does not contain DEFAULT_NOTAS
        defaultCDetalleReservacionShouldNotBeFound("notas.doesNotContain=" + DEFAULT_NOTAS);

        // Get all the cDetalleReservacionList where notas does not contain UPDATED_NOTAS
        defaultCDetalleReservacionShouldBeFound("notas.doesNotContain=" + UPDATED_NOTAS);
    }


    @Test
    @Transactional
    public void getAllCDetalleReservacionsByEstatusIsEqualToSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where estatus equals to DEFAULT_ESTATUS
        defaultCDetalleReservacionShouldBeFound("estatus.equals=" + DEFAULT_ESTATUS);

        // Get all the cDetalleReservacionList where estatus equals to UPDATED_ESTATUS
        defaultCDetalleReservacionShouldNotBeFound("estatus.equals=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByEstatusIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where estatus not equals to DEFAULT_ESTATUS
        defaultCDetalleReservacionShouldNotBeFound("estatus.notEquals=" + DEFAULT_ESTATUS);

        // Get all the cDetalleReservacionList where estatus not equals to UPDATED_ESTATUS
        defaultCDetalleReservacionShouldBeFound("estatus.notEquals=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByEstatusIsInShouldWork() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where estatus in DEFAULT_ESTATUS or UPDATED_ESTATUS
        defaultCDetalleReservacionShouldBeFound("estatus.in=" + DEFAULT_ESTATUS + "," + UPDATED_ESTATUS);

        // Get all the cDetalleReservacionList where estatus equals to UPDATED_ESTATUS
        defaultCDetalleReservacionShouldNotBeFound("estatus.in=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByEstatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where estatus is not null
        defaultCDetalleReservacionShouldBeFound("estatus.specified=true");

        // Get all the cDetalleReservacionList where estatus is null
        defaultCDetalleReservacionShouldNotBeFound("estatus.specified=false");
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByEstatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where estatus is greater than or equal to DEFAULT_ESTATUS
        defaultCDetalleReservacionShouldBeFound("estatus.greaterThanOrEqual=" + DEFAULT_ESTATUS);

        // Get all the cDetalleReservacionList where estatus is greater than or equal to (DEFAULT_ESTATUS + 1)
        defaultCDetalleReservacionShouldNotBeFound("estatus.greaterThanOrEqual=" + (DEFAULT_ESTATUS + 1));
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByEstatusIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where estatus is less than or equal to DEFAULT_ESTATUS
        defaultCDetalleReservacionShouldBeFound("estatus.lessThanOrEqual=" + DEFAULT_ESTATUS);

        // Get all the cDetalleReservacionList where estatus is less than or equal to SMALLER_ESTATUS
        defaultCDetalleReservacionShouldNotBeFound("estatus.lessThanOrEqual=" + SMALLER_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByEstatusIsLessThanSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where estatus is less than DEFAULT_ESTATUS
        defaultCDetalleReservacionShouldNotBeFound("estatus.lessThan=" + DEFAULT_ESTATUS);

        // Get all the cDetalleReservacionList where estatus is less than (DEFAULT_ESTATUS + 1)
        defaultCDetalleReservacionShouldBeFound("estatus.lessThan=" + (DEFAULT_ESTATUS + 1));
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByEstatusIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where estatus is greater than DEFAULT_ESTATUS
        defaultCDetalleReservacionShouldNotBeFound("estatus.greaterThan=" + DEFAULT_ESTATUS);

        // Get all the cDetalleReservacionList where estatus is greater than SMALLER_ESTATUS
        defaultCDetalleReservacionShouldBeFound("estatus.greaterThan=" + SMALLER_ESTATUS);
    }


    @Test
    @Transactional
    public void getAllCDetalleReservacionsByBorradoIsEqualToSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where borrado equals to DEFAULT_BORRADO
        defaultCDetalleReservacionShouldBeFound("borrado.equals=" + DEFAULT_BORRADO);

        // Get all the cDetalleReservacionList where borrado equals to UPDATED_BORRADO
        defaultCDetalleReservacionShouldNotBeFound("borrado.equals=" + UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByBorradoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where borrado not equals to DEFAULT_BORRADO
        defaultCDetalleReservacionShouldNotBeFound("borrado.notEquals=" + DEFAULT_BORRADO);

        // Get all the cDetalleReservacionList where borrado not equals to UPDATED_BORRADO
        defaultCDetalleReservacionShouldBeFound("borrado.notEquals=" + UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByBorradoIsInShouldWork() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where borrado in DEFAULT_BORRADO or UPDATED_BORRADO
        defaultCDetalleReservacionShouldBeFound("borrado.in=" + DEFAULT_BORRADO + "," + UPDATED_BORRADO);

        // Get all the cDetalleReservacionList where borrado equals to UPDATED_BORRADO
        defaultCDetalleReservacionShouldNotBeFound("borrado.in=" + UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByBorradoIsNullOrNotNull() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where borrado is not null
        defaultCDetalleReservacionShouldBeFound("borrado.specified=true");

        // Get all the cDetalleReservacionList where borrado is null
        defaultCDetalleReservacionShouldNotBeFound("borrado.specified=false");
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByBorradoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where borrado is greater than or equal to DEFAULT_BORRADO
        defaultCDetalleReservacionShouldBeFound("borrado.greaterThanOrEqual=" + DEFAULT_BORRADO);

        // Get all the cDetalleReservacionList where borrado is greater than or equal to (DEFAULT_BORRADO + 1)
        defaultCDetalleReservacionShouldNotBeFound("borrado.greaterThanOrEqual=" + (DEFAULT_BORRADO + 1));
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByBorradoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where borrado is less than or equal to DEFAULT_BORRADO
        defaultCDetalleReservacionShouldBeFound("borrado.lessThanOrEqual=" + DEFAULT_BORRADO);

        // Get all the cDetalleReservacionList where borrado is less than or equal to SMALLER_BORRADO
        defaultCDetalleReservacionShouldNotBeFound("borrado.lessThanOrEqual=" + SMALLER_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByBorradoIsLessThanSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where borrado is less than DEFAULT_BORRADO
        defaultCDetalleReservacionShouldNotBeFound("borrado.lessThan=" + DEFAULT_BORRADO);

        // Get all the cDetalleReservacionList where borrado is less than (DEFAULT_BORRADO + 1)
        defaultCDetalleReservacionShouldBeFound("borrado.lessThan=" + (DEFAULT_BORRADO + 1));
    }

    @Test
    @Transactional
    public void getAllCDetalleReservacionsByBorradoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        // Get all the cDetalleReservacionList where borrado is greater than DEFAULT_BORRADO
        defaultCDetalleReservacionShouldNotBeFound("borrado.greaterThan=" + DEFAULT_BORRADO);

        // Get all the cDetalleReservacionList where borrado is greater than SMALLER_BORRADO
        defaultCDetalleReservacionShouldBeFound("borrado.greaterThan=" + SMALLER_BORRADO);
    }


    @Test
    @Transactional
    public void getAllCDetalleReservacionsByClienteIsEqualToSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);
        CCliente cliente = CClienteResourceIT.createEntity(em);
        em.persist(cliente);
        em.flush();
        cDetalleReservacion.setCliente(cliente);
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);
        Long clienteId = cliente.getId();

        // Get all the cDetalleReservacionList where cliente equals to clienteId
        defaultCDetalleReservacionShouldBeFound("clienteId.equals=" + clienteId);

        // Get all the cDetalleReservacionList where cliente equals to clienteId + 1
        defaultCDetalleReservacionShouldNotBeFound("clienteId.equals=" + (clienteId + 1));
    }


    @Test
    @Transactional
    public void getAllCDetalleReservacionsByPasajeroIsEqualToSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);
        CPasajero pasajero = CPasajeroResourceIT.createEntity(em);
        em.persist(pasajero);
        em.flush();
        cDetalleReservacion.setPasajero(pasajero);
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);
        Long pasajeroId = pasajero.getId();

        // Get all the cDetalleReservacionList where pasajero equals to pasajeroId
        defaultCDetalleReservacionShouldBeFound("pasajeroId.equals=" + pasajeroId);

        // Get all the cDetalleReservacionList where pasajero equals to pasajeroId + 1
        defaultCDetalleReservacionShouldNotBeFound("pasajeroId.equals=" + (pasajeroId + 1));
    }


    @Test
    @Transactional
    public void getAllCDetalleReservacionsByReservacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);
        CReservacion reservacion = CReservacionResourceIT.createEntity(em);
        em.persist(reservacion);
        em.flush();
        cDetalleReservacion.setReservacion(reservacion);
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);
        Long reservacionId = reservacion.getId();

        // Get all the cDetalleReservacionList where reservacion equals to reservacionId
        defaultCDetalleReservacionShouldBeFound("reservacionId.equals=" + reservacionId);

        // Get all the cDetalleReservacionList where reservacion equals to reservacionId + 1
        defaultCDetalleReservacionShouldNotBeFound("reservacionId.equals=" + (reservacionId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCDetalleReservacionShouldBeFound(String filter) throws Exception {
        restCDetalleReservacionMockMvc.perform(get("/api/c-detalle-reservacions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cDetalleReservacion.getId().intValue())))
            .andExpect(jsonPath("$.[*].precio").value(hasItem(DEFAULT_PRECIO.doubleValue())))
            .andExpect(jsonPath("$.[*].idUsuarioCreacion").value(hasItem(DEFAULT_ID_USUARIO_CREACION.intValue())))
            .andExpect(jsonPath("$.[*].fechaCreacion").value(hasItem(DEFAULT_FECHA_CREACION.toString())))
            .andExpect(jsonPath("$.[*].idUsuarioActualizacion").value(hasItem(DEFAULT_ID_USUARIO_ACTUALIZACION.intValue())))
            .andExpect(jsonPath("$.[*].fechaActualizacion").value(hasItem(DEFAULT_FECHA_ACTUALIZACION.toString())))
            .andExpect(jsonPath("$.[*].notas").value(hasItem(DEFAULT_NOTAS)))
            .andExpect(jsonPath("$.[*].estatus").value(hasItem(DEFAULT_ESTATUS)))
            .andExpect(jsonPath("$.[*].borrado").value(hasItem(DEFAULT_BORRADO)));

        // Check, that the count call also returns 1
        restCDetalleReservacionMockMvc.perform(get("/api/c-detalle-reservacions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCDetalleReservacionShouldNotBeFound(String filter) throws Exception {
        restCDetalleReservacionMockMvc.perform(get("/api/c-detalle-reservacions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCDetalleReservacionMockMvc.perform(get("/api/c-detalle-reservacions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCDetalleReservacion() throws Exception {
        // Get the cDetalleReservacion
        restCDetalleReservacionMockMvc.perform(get("/api/c-detalle-reservacions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCDetalleReservacion() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        int databaseSizeBeforeUpdate = cDetalleReservacionRepository.findAll().size();

        // Update the cDetalleReservacion
        CDetalleReservacion updatedCDetalleReservacion = cDetalleReservacionRepository.findById(cDetalleReservacion.getId()).get();
        // Disconnect from session so that the updates on updatedCDetalleReservacion are not directly saved in db
        em.detach(updatedCDetalleReservacion);
        updatedCDetalleReservacion
            .precio(UPDATED_PRECIO)
            .idUsuarioCreacion(UPDATED_ID_USUARIO_CREACION)
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .idUsuarioActualizacion(UPDATED_ID_USUARIO_ACTUALIZACION)
            .fechaActualizacion(UPDATED_FECHA_ACTUALIZACION)
            .notas(UPDATED_NOTAS)
            .estatus(UPDATED_ESTATUS)
            .borrado(UPDATED_BORRADO);
        CDetalleReservacionDTO cDetalleReservacionDTO = cDetalleReservacionMapper.toDto(updatedCDetalleReservacion);

        restCDetalleReservacionMockMvc.perform(put("/api/c-detalle-reservacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cDetalleReservacionDTO)))
            .andExpect(status().isOk());

        // Validate the CDetalleReservacion in the database
        List<CDetalleReservacion> cDetalleReservacionList = cDetalleReservacionRepository.findAll();
        assertThat(cDetalleReservacionList).hasSize(databaseSizeBeforeUpdate);
        CDetalleReservacion testCDetalleReservacion = cDetalleReservacionList.get(cDetalleReservacionList.size() - 1);
        assertThat(testCDetalleReservacion.getPrecio()).isEqualTo(UPDATED_PRECIO);
        assertThat(testCDetalleReservacion.getIdUsuarioCreacion()).isEqualTo(UPDATED_ID_USUARIO_CREACION);
        assertThat(testCDetalleReservacion.getFechaCreacion()).isEqualTo(UPDATED_FECHA_CREACION);
        assertThat(testCDetalleReservacion.getIdUsuarioActualizacion()).isEqualTo(UPDATED_ID_USUARIO_ACTUALIZACION);
        assertThat(testCDetalleReservacion.getFechaActualizacion()).isEqualTo(UPDATED_FECHA_ACTUALIZACION);
        assertThat(testCDetalleReservacion.getNotas()).isEqualTo(UPDATED_NOTAS);
        assertThat(testCDetalleReservacion.getEstatus()).isEqualTo(UPDATED_ESTATUS);
        assertThat(testCDetalleReservacion.getBorrado()).isEqualTo(UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void updateNonExistingCDetalleReservacion() throws Exception {
        int databaseSizeBeforeUpdate = cDetalleReservacionRepository.findAll().size();

        // Create the CDetalleReservacion
        CDetalleReservacionDTO cDetalleReservacionDTO = cDetalleReservacionMapper.toDto(cDetalleReservacion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCDetalleReservacionMockMvc.perform(put("/api/c-detalle-reservacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cDetalleReservacionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CDetalleReservacion in the database
        List<CDetalleReservacion> cDetalleReservacionList = cDetalleReservacionRepository.findAll();
        assertThat(cDetalleReservacionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCDetalleReservacion() throws Exception {
        // Initialize the database
        cDetalleReservacionRepository.saveAndFlush(cDetalleReservacion);

        int databaseSizeBeforeDelete = cDetalleReservacionRepository.findAll().size();

        // Delete the cDetalleReservacion
        restCDetalleReservacionMockMvc.perform(delete("/api/c-detalle-reservacions/{id}", cDetalleReservacion.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CDetalleReservacion> cDetalleReservacionList = cDetalleReservacionRepository.findAll();
        assertThat(cDetalleReservacionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
