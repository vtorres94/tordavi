package mx.com.tordavi.web.rest;

import mx.com.tordavi.TordaviApp;
import mx.com.tordavi.domain.CDireccion;
import mx.com.tordavi.domain.CCliente;
import mx.com.tordavi.domain.CPasajero;
import mx.com.tordavi.repository.CDireccionRepository;
import mx.com.tordavi.service.CDireccionService;
import mx.com.tordavi.service.dto.CDireccionDTO;
import mx.com.tordavi.service.mapper.CDireccionMapper;
import mx.com.tordavi.service.dto.CDireccionCriteria;
import mx.com.tordavi.service.CDireccionQueryService;

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
 * Integration tests for the {@link CDireccionResource} REST controller.
 */
@SpringBootTest(classes = TordaviApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class CDireccionResourceIT {

    private static final String DEFAULT_DIRECCION_COMPLETE = "AAAAAAAAAA";
    private static final String UPDATED_DIRECCION_COMPLETE = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECCION = "AAAAAAAAAA";
    private static final String UPDATED_DIRECCION = "BBBBBBBBBB";

    private static final String DEFAULT_NUM_EXTERIOR = "AAAAAAAAAA";
    private static final String UPDATED_NUM_EXTERIOR = "BBBBBBBBBB";

    private static final String DEFAULT_NUM_INTERIOR = "AAAAAAAAAA";
    private static final String UPDATED_NUM_INTERIOR = "BBBBBBBBBB";

    private static final Integer DEFAULT_CODIGO_POSTAL = 99999999;
    private static final Integer UPDATED_CODIGO_POSTAL = 99999998;
    private static final Integer SMALLER_CODIGO_POSTAL = 99999999 - 1;

    private static final String DEFAULT_CIUDAD = "AAAAAAAAAA";
    private static final String UPDATED_CIUDAD = "BBBBBBBBBB";

    private static final String DEFAULT_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_ESTADO = "BBBBBBBBBB";

    private static final String DEFAULT_PAIS = "AAAAAAAAAA";
    private static final String UPDATED_PAIS = "BBBBBBBBBB";

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

    private static final Integer DEFAULT_ESTATUS = 1;
    private static final Integer UPDATED_ESTATUS = 0;
    private static final Integer SMALLER_ESTATUS = 1 - 1;

    private static final Integer DEFAULT_BORRADO = 1;
    private static final Integer UPDATED_BORRADO = 0;
    private static final Integer SMALLER_BORRADO = 1 - 1;

    @Autowired
    private CDireccionRepository cDireccionRepository;

    @Autowired
    private CDireccionMapper cDireccionMapper;

    @Autowired
    private CDireccionService cDireccionService;

    @Autowired
    private CDireccionQueryService cDireccionQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCDireccionMockMvc;

    private CDireccion cDireccion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CDireccion createEntity(EntityManager em) {
        CDireccion cDireccion = new CDireccion()
            .direccionComplete(DEFAULT_DIRECCION_COMPLETE)
            .direccion(DEFAULT_DIRECCION)
            .numExterior(DEFAULT_NUM_EXTERIOR)
            .numInterior(DEFAULT_NUM_INTERIOR)
            .codigoPostal(DEFAULT_CODIGO_POSTAL)
            .ciudad(DEFAULT_CIUDAD)
            .estado(DEFAULT_ESTADO)
            .pais(DEFAULT_PAIS)
            .idUsuarioCreacion(DEFAULT_ID_USUARIO_CREACION)
            .fechaCreacion(DEFAULT_FECHA_CREACION)
            .idUsuarioActualizacion(DEFAULT_ID_USUARIO_ACTUALIZACION)
            .fechaActualizacion(DEFAULT_FECHA_ACTUALIZACION)
            .notas(DEFAULT_NOTAS)
            .estatus(DEFAULT_ESTATUS)
            .borrado(DEFAULT_BORRADO);
        return cDireccion;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CDireccion createUpdatedEntity(EntityManager em) {
        CDireccion cDireccion = new CDireccion()
            .direccionComplete(UPDATED_DIRECCION_COMPLETE)
            .direccion(UPDATED_DIRECCION)
            .numExterior(UPDATED_NUM_EXTERIOR)
            .numInterior(UPDATED_NUM_INTERIOR)
            .codigoPostal(UPDATED_CODIGO_POSTAL)
            .ciudad(UPDATED_CIUDAD)
            .estado(UPDATED_ESTADO)
            .pais(UPDATED_PAIS)
            .idUsuarioCreacion(UPDATED_ID_USUARIO_CREACION)
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .idUsuarioActualizacion(UPDATED_ID_USUARIO_ACTUALIZACION)
            .fechaActualizacion(UPDATED_FECHA_ACTUALIZACION)
            .notas(UPDATED_NOTAS)
            .estatus(UPDATED_ESTATUS)
            .borrado(UPDATED_BORRADO);
        return cDireccion;
    }

    @BeforeEach
    public void initTest() {
        cDireccion = createEntity(em);
    }

    @Test
    @Transactional
    public void createCDireccion() throws Exception {
        int databaseSizeBeforeCreate = cDireccionRepository.findAll().size();

        // Create the CDireccion
        CDireccionDTO cDireccionDTO = cDireccionMapper.toDto(cDireccion);
        restCDireccionMockMvc.perform(post("/api/c-direccions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cDireccionDTO)))
            .andExpect(status().isCreated());

        // Validate the CDireccion in the database
        List<CDireccion> cDireccionList = cDireccionRepository.findAll();
        assertThat(cDireccionList).hasSize(databaseSizeBeforeCreate + 1);
        CDireccion testCDireccion = cDireccionList.get(cDireccionList.size() - 1);
        assertThat(testCDireccion.getDireccionComplete()).isEqualTo(DEFAULT_DIRECCION_COMPLETE);
        assertThat(testCDireccion.getDireccion()).isEqualTo(DEFAULT_DIRECCION);
        assertThat(testCDireccion.getNumExterior()).isEqualTo(DEFAULT_NUM_EXTERIOR);
        assertThat(testCDireccion.getNumInterior()).isEqualTo(DEFAULT_NUM_INTERIOR);
        assertThat(testCDireccion.getCodigoPostal()).isEqualTo(DEFAULT_CODIGO_POSTAL);
        assertThat(testCDireccion.getCiudad()).isEqualTo(DEFAULT_CIUDAD);
        assertThat(testCDireccion.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testCDireccion.getPais()).isEqualTo(DEFAULT_PAIS);
        assertThat(testCDireccion.getIdUsuarioCreacion()).isEqualTo(DEFAULT_ID_USUARIO_CREACION);
        assertThat(testCDireccion.getFechaCreacion()).isEqualTo(DEFAULT_FECHA_CREACION);
        assertThat(testCDireccion.getIdUsuarioActualizacion()).isEqualTo(DEFAULT_ID_USUARIO_ACTUALIZACION);
        assertThat(testCDireccion.getFechaActualizacion()).isEqualTo(DEFAULT_FECHA_ACTUALIZACION);
        assertThat(testCDireccion.getNotas()).isEqualTo(DEFAULT_NOTAS);
        assertThat(testCDireccion.getEstatus()).isEqualTo(DEFAULT_ESTATUS);
        assertThat(testCDireccion.getBorrado()).isEqualTo(DEFAULT_BORRADO);
    }

    @Test
    @Transactional
    public void createCDireccionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cDireccionRepository.findAll().size();

        // Create the CDireccion with an existing ID
        cDireccion.setId(1L);
        CDireccionDTO cDireccionDTO = cDireccionMapper.toDto(cDireccion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCDireccionMockMvc.perform(post("/api/c-direccions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cDireccionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CDireccion in the database
        List<CDireccion> cDireccionList = cDireccionRepository.findAll();
        assertThat(cDireccionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdUsuarioCreacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = cDireccionRepository.findAll().size();
        // set the field null
        cDireccion.setIdUsuarioCreacion(null);

        // Create the CDireccion, which fails.
        CDireccionDTO cDireccionDTO = cDireccionMapper.toDto(cDireccion);

        restCDireccionMockMvc.perform(post("/api/c-direccions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cDireccionDTO)))
            .andExpect(status().isBadRequest());

        List<CDireccion> cDireccionList = cDireccionRepository.findAll();
        assertThat(cDireccionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFechaCreacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = cDireccionRepository.findAll().size();
        // set the field null
        cDireccion.setFechaCreacion(null);

        // Create the CDireccion, which fails.
        CDireccionDTO cDireccionDTO = cDireccionMapper.toDto(cDireccion);

        restCDireccionMockMvc.perform(post("/api/c-direccions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cDireccionDTO)))
            .andExpect(status().isBadRequest());

        List<CDireccion> cDireccionList = cDireccionRepository.findAll();
        assertThat(cDireccionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = cDireccionRepository.findAll().size();
        // set the field null
        cDireccion.setEstatus(null);

        // Create the CDireccion, which fails.
        CDireccionDTO cDireccionDTO = cDireccionMapper.toDto(cDireccion);

        restCDireccionMockMvc.perform(post("/api/c-direccions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cDireccionDTO)))
            .andExpect(status().isBadRequest());

        List<CDireccion> cDireccionList = cDireccionRepository.findAll();
        assertThat(cDireccionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBorradoIsRequired() throws Exception {
        int databaseSizeBeforeTest = cDireccionRepository.findAll().size();
        // set the field null
        cDireccion.setBorrado(null);

        // Create the CDireccion, which fails.
        CDireccionDTO cDireccionDTO = cDireccionMapper.toDto(cDireccion);

        restCDireccionMockMvc.perform(post("/api/c-direccions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cDireccionDTO)))
            .andExpect(status().isBadRequest());

        List<CDireccion> cDireccionList = cDireccionRepository.findAll();
        assertThat(cDireccionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCDireccions() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList
        restCDireccionMockMvc.perform(get("/api/c-direccions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cDireccion.getId().intValue())))
            .andExpect(jsonPath("$.[*].direccionComplete").value(hasItem(DEFAULT_DIRECCION_COMPLETE)))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION)))
            .andExpect(jsonPath("$.[*].numExterior").value(hasItem(DEFAULT_NUM_EXTERIOR)))
            .andExpect(jsonPath("$.[*].numInterior").value(hasItem(DEFAULT_NUM_INTERIOR)))
            .andExpect(jsonPath("$.[*].codigoPostal").value(hasItem(DEFAULT_CODIGO_POSTAL)))
            .andExpect(jsonPath("$.[*].ciudad").value(hasItem(DEFAULT_CIUDAD)))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO)))
            .andExpect(jsonPath("$.[*].pais").value(hasItem(DEFAULT_PAIS)))
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
    public void getCDireccion() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get the cDireccion
        restCDireccionMockMvc.perform(get("/api/c-direccions/{id}", cDireccion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cDireccion.getId().intValue()))
            .andExpect(jsonPath("$.direccionComplete").value(DEFAULT_DIRECCION_COMPLETE))
            .andExpect(jsonPath("$.direccion").value(DEFAULT_DIRECCION))
            .andExpect(jsonPath("$.numExterior").value(DEFAULT_NUM_EXTERIOR))
            .andExpect(jsonPath("$.numInterior").value(DEFAULT_NUM_INTERIOR))
            .andExpect(jsonPath("$.codigoPostal").value(DEFAULT_CODIGO_POSTAL))
            .andExpect(jsonPath("$.ciudad").value(DEFAULT_CIUDAD))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO))
            .andExpect(jsonPath("$.pais").value(DEFAULT_PAIS))
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
    public void getCDireccionsByIdFiltering() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        Long id = cDireccion.getId();

        defaultCDireccionShouldBeFound("id.equals=" + id);
        defaultCDireccionShouldNotBeFound("id.notEquals=" + id);

        defaultCDireccionShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCDireccionShouldNotBeFound("id.greaterThan=" + id);

        defaultCDireccionShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCDireccionShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllCDireccionsByDireccionCompleteIsEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where direccionComplete equals to DEFAULT_DIRECCION_COMPLETE
        defaultCDireccionShouldBeFound("direccionComplete.equals=" + DEFAULT_DIRECCION_COMPLETE);

        // Get all the cDireccionList where direccionComplete equals to UPDATED_DIRECCION_COMPLETE
        defaultCDireccionShouldNotBeFound("direccionComplete.equals=" + UPDATED_DIRECCION_COMPLETE);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByDireccionCompleteIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where direccionComplete not equals to DEFAULT_DIRECCION_COMPLETE
        defaultCDireccionShouldNotBeFound("direccionComplete.notEquals=" + DEFAULT_DIRECCION_COMPLETE);

        // Get all the cDireccionList where direccionComplete not equals to UPDATED_DIRECCION_COMPLETE
        defaultCDireccionShouldBeFound("direccionComplete.notEquals=" + UPDATED_DIRECCION_COMPLETE);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByDireccionCompleteIsInShouldWork() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where direccionComplete in DEFAULT_DIRECCION_COMPLETE or UPDATED_DIRECCION_COMPLETE
        defaultCDireccionShouldBeFound("direccionComplete.in=" + DEFAULT_DIRECCION_COMPLETE + "," + UPDATED_DIRECCION_COMPLETE);

        // Get all the cDireccionList where direccionComplete equals to UPDATED_DIRECCION_COMPLETE
        defaultCDireccionShouldNotBeFound("direccionComplete.in=" + UPDATED_DIRECCION_COMPLETE);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByDireccionCompleteIsNullOrNotNull() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where direccionComplete is not null
        defaultCDireccionShouldBeFound("direccionComplete.specified=true");

        // Get all the cDireccionList where direccionComplete is null
        defaultCDireccionShouldNotBeFound("direccionComplete.specified=false");
    }
                @Test
    @Transactional
    public void getAllCDireccionsByDireccionCompleteContainsSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where direccionComplete contains DEFAULT_DIRECCION_COMPLETE
        defaultCDireccionShouldBeFound("direccionComplete.contains=" + DEFAULT_DIRECCION_COMPLETE);

        // Get all the cDireccionList where direccionComplete contains UPDATED_DIRECCION_COMPLETE
        defaultCDireccionShouldNotBeFound("direccionComplete.contains=" + UPDATED_DIRECCION_COMPLETE);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByDireccionCompleteNotContainsSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where direccionComplete does not contain DEFAULT_DIRECCION_COMPLETE
        defaultCDireccionShouldNotBeFound("direccionComplete.doesNotContain=" + DEFAULT_DIRECCION_COMPLETE);

        // Get all the cDireccionList where direccionComplete does not contain UPDATED_DIRECCION_COMPLETE
        defaultCDireccionShouldBeFound("direccionComplete.doesNotContain=" + UPDATED_DIRECCION_COMPLETE);
    }


    @Test
    @Transactional
    public void getAllCDireccionsByDireccionIsEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where direccion equals to DEFAULT_DIRECCION
        defaultCDireccionShouldBeFound("direccion.equals=" + DEFAULT_DIRECCION);

        // Get all the cDireccionList where direccion equals to UPDATED_DIRECCION
        defaultCDireccionShouldNotBeFound("direccion.equals=" + UPDATED_DIRECCION);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByDireccionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where direccion not equals to DEFAULT_DIRECCION
        defaultCDireccionShouldNotBeFound("direccion.notEquals=" + DEFAULT_DIRECCION);

        // Get all the cDireccionList where direccion not equals to UPDATED_DIRECCION
        defaultCDireccionShouldBeFound("direccion.notEquals=" + UPDATED_DIRECCION);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByDireccionIsInShouldWork() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where direccion in DEFAULT_DIRECCION or UPDATED_DIRECCION
        defaultCDireccionShouldBeFound("direccion.in=" + DEFAULT_DIRECCION + "," + UPDATED_DIRECCION);

        // Get all the cDireccionList where direccion equals to UPDATED_DIRECCION
        defaultCDireccionShouldNotBeFound("direccion.in=" + UPDATED_DIRECCION);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByDireccionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where direccion is not null
        defaultCDireccionShouldBeFound("direccion.specified=true");

        // Get all the cDireccionList where direccion is null
        defaultCDireccionShouldNotBeFound("direccion.specified=false");
    }
                @Test
    @Transactional
    public void getAllCDireccionsByDireccionContainsSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where direccion contains DEFAULT_DIRECCION
        defaultCDireccionShouldBeFound("direccion.contains=" + DEFAULT_DIRECCION);

        // Get all the cDireccionList where direccion contains UPDATED_DIRECCION
        defaultCDireccionShouldNotBeFound("direccion.contains=" + UPDATED_DIRECCION);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByDireccionNotContainsSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where direccion does not contain DEFAULT_DIRECCION
        defaultCDireccionShouldNotBeFound("direccion.doesNotContain=" + DEFAULT_DIRECCION);

        // Get all the cDireccionList where direccion does not contain UPDATED_DIRECCION
        defaultCDireccionShouldBeFound("direccion.doesNotContain=" + UPDATED_DIRECCION);
    }


    @Test
    @Transactional
    public void getAllCDireccionsByNumExteriorIsEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where numExterior equals to DEFAULT_NUM_EXTERIOR
        defaultCDireccionShouldBeFound("numExterior.equals=" + DEFAULT_NUM_EXTERIOR);

        // Get all the cDireccionList where numExterior equals to UPDATED_NUM_EXTERIOR
        defaultCDireccionShouldNotBeFound("numExterior.equals=" + UPDATED_NUM_EXTERIOR);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByNumExteriorIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where numExterior not equals to DEFAULT_NUM_EXTERIOR
        defaultCDireccionShouldNotBeFound("numExterior.notEquals=" + DEFAULT_NUM_EXTERIOR);

        // Get all the cDireccionList where numExterior not equals to UPDATED_NUM_EXTERIOR
        defaultCDireccionShouldBeFound("numExterior.notEquals=" + UPDATED_NUM_EXTERIOR);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByNumExteriorIsInShouldWork() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where numExterior in DEFAULT_NUM_EXTERIOR or UPDATED_NUM_EXTERIOR
        defaultCDireccionShouldBeFound("numExterior.in=" + DEFAULT_NUM_EXTERIOR + "," + UPDATED_NUM_EXTERIOR);

        // Get all the cDireccionList where numExterior equals to UPDATED_NUM_EXTERIOR
        defaultCDireccionShouldNotBeFound("numExterior.in=" + UPDATED_NUM_EXTERIOR);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByNumExteriorIsNullOrNotNull() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where numExterior is not null
        defaultCDireccionShouldBeFound("numExterior.specified=true");

        // Get all the cDireccionList where numExterior is null
        defaultCDireccionShouldNotBeFound("numExterior.specified=false");
    }
                @Test
    @Transactional
    public void getAllCDireccionsByNumExteriorContainsSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where numExterior contains DEFAULT_NUM_EXTERIOR
        defaultCDireccionShouldBeFound("numExterior.contains=" + DEFAULT_NUM_EXTERIOR);

        // Get all the cDireccionList where numExterior contains UPDATED_NUM_EXTERIOR
        defaultCDireccionShouldNotBeFound("numExterior.contains=" + UPDATED_NUM_EXTERIOR);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByNumExteriorNotContainsSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where numExterior does not contain DEFAULT_NUM_EXTERIOR
        defaultCDireccionShouldNotBeFound("numExterior.doesNotContain=" + DEFAULT_NUM_EXTERIOR);

        // Get all the cDireccionList where numExterior does not contain UPDATED_NUM_EXTERIOR
        defaultCDireccionShouldBeFound("numExterior.doesNotContain=" + UPDATED_NUM_EXTERIOR);
    }


    @Test
    @Transactional
    public void getAllCDireccionsByNumInteriorIsEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where numInterior equals to DEFAULT_NUM_INTERIOR
        defaultCDireccionShouldBeFound("numInterior.equals=" + DEFAULT_NUM_INTERIOR);

        // Get all the cDireccionList where numInterior equals to UPDATED_NUM_INTERIOR
        defaultCDireccionShouldNotBeFound("numInterior.equals=" + UPDATED_NUM_INTERIOR);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByNumInteriorIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where numInterior not equals to DEFAULT_NUM_INTERIOR
        defaultCDireccionShouldNotBeFound("numInterior.notEquals=" + DEFAULT_NUM_INTERIOR);

        // Get all the cDireccionList where numInterior not equals to UPDATED_NUM_INTERIOR
        defaultCDireccionShouldBeFound("numInterior.notEquals=" + UPDATED_NUM_INTERIOR);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByNumInteriorIsInShouldWork() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where numInterior in DEFAULT_NUM_INTERIOR or UPDATED_NUM_INTERIOR
        defaultCDireccionShouldBeFound("numInterior.in=" + DEFAULT_NUM_INTERIOR + "," + UPDATED_NUM_INTERIOR);

        // Get all the cDireccionList where numInterior equals to UPDATED_NUM_INTERIOR
        defaultCDireccionShouldNotBeFound("numInterior.in=" + UPDATED_NUM_INTERIOR);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByNumInteriorIsNullOrNotNull() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where numInterior is not null
        defaultCDireccionShouldBeFound("numInterior.specified=true");

        // Get all the cDireccionList where numInterior is null
        defaultCDireccionShouldNotBeFound("numInterior.specified=false");
    }
                @Test
    @Transactional
    public void getAllCDireccionsByNumInteriorContainsSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where numInterior contains DEFAULT_NUM_INTERIOR
        defaultCDireccionShouldBeFound("numInterior.contains=" + DEFAULT_NUM_INTERIOR);

        // Get all the cDireccionList where numInterior contains UPDATED_NUM_INTERIOR
        defaultCDireccionShouldNotBeFound("numInterior.contains=" + UPDATED_NUM_INTERIOR);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByNumInteriorNotContainsSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where numInterior does not contain DEFAULT_NUM_INTERIOR
        defaultCDireccionShouldNotBeFound("numInterior.doesNotContain=" + DEFAULT_NUM_INTERIOR);

        // Get all the cDireccionList where numInterior does not contain UPDATED_NUM_INTERIOR
        defaultCDireccionShouldBeFound("numInterior.doesNotContain=" + UPDATED_NUM_INTERIOR);
    }


    @Test
    @Transactional
    public void getAllCDireccionsByCodigoPostalIsEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where codigoPostal equals to DEFAULT_CODIGO_POSTAL
        defaultCDireccionShouldBeFound("codigoPostal.equals=" + DEFAULT_CODIGO_POSTAL);

        // Get all the cDireccionList where codigoPostal equals to UPDATED_CODIGO_POSTAL
        defaultCDireccionShouldNotBeFound("codigoPostal.equals=" + UPDATED_CODIGO_POSTAL);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByCodigoPostalIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where codigoPostal not equals to DEFAULT_CODIGO_POSTAL
        defaultCDireccionShouldNotBeFound("codigoPostal.notEquals=" + DEFAULT_CODIGO_POSTAL);

        // Get all the cDireccionList where codigoPostal not equals to UPDATED_CODIGO_POSTAL
        defaultCDireccionShouldBeFound("codigoPostal.notEquals=" + UPDATED_CODIGO_POSTAL);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByCodigoPostalIsInShouldWork() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where codigoPostal in DEFAULT_CODIGO_POSTAL or UPDATED_CODIGO_POSTAL
        defaultCDireccionShouldBeFound("codigoPostal.in=" + DEFAULT_CODIGO_POSTAL + "," + UPDATED_CODIGO_POSTAL);

        // Get all the cDireccionList where codigoPostal equals to UPDATED_CODIGO_POSTAL
        defaultCDireccionShouldNotBeFound("codigoPostal.in=" + UPDATED_CODIGO_POSTAL);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByCodigoPostalIsNullOrNotNull() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where codigoPostal is not null
        defaultCDireccionShouldBeFound("codigoPostal.specified=true");

        // Get all the cDireccionList where codigoPostal is null
        defaultCDireccionShouldNotBeFound("codigoPostal.specified=false");
    }

    @Test
    @Transactional
    public void getAllCDireccionsByCodigoPostalIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where codigoPostal is greater than or equal to DEFAULT_CODIGO_POSTAL
        defaultCDireccionShouldBeFound("codigoPostal.greaterThanOrEqual=" + DEFAULT_CODIGO_POSTAL);

        // Get all the cDireccionList where codigoPostal is greater than or equal to (DEFAULT_CODIGO_POSTAL + 1)
        defaultCDireccionShouldNotBeFound("codigoPostal.greaterThanOrEqual=" + (DEFAULT_CODIGO_POSTAL + 1));
    }

    @Test
    @Transactional
    public void getAllCDireccionsByCodigoPostalIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where codigoPostal is less than or equal to DEFAULT_CODIGO_POSTAL
        defaultCDireccionShouldBeFound("codigoPostal.lessThanOrEqual=" + DEFAULT_CODIGO_POSTAL);

        // Get all the cDireccionList where codigoPostal is less than or equal to SMALLER_CODIGO_POSTAL
        defaultCDireccionShouldNotBeFound("codigoPostal.lessThanOrEqual=" + SMALLER_CODIGO_POSTAL);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByCodigoPostalIsLessThanSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where codigoPostal is less than DEFAULT_CODIGO_POSTAL
        defaultCDireccionShouldNotBeFound("codigoPostal.lessThan=" + DEFAULT_CODIGO_POSTAL);

        // Get all the cDireccionList where codigoPostal is less than (DEFAULT_CODIGO_POSTAL + 1)
        defaultCDireccionShouldBeFound("codigoPostal.lessThan=" + (DEFAULT_CODIGO_POSTAL + 1));
    }

    @Test
    @Transactional
    public void getAllCDireccionsByCodigoPostalIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where codigoPostal is greater than DEFAULT_CODIGO_POSTAL
        defaultCDireccionShouldNotBeFound("codigoPostal.greaterThan=" + DEFAULT_CODIGO_POSTAL);

        // Get all the cDireccionList where codigoPostal is greater than SMALLER_CODIGO_POSTAL
        defaultCDireccionShouldBeFound("codigoPostal.greaterThan=" + SMALLER_CODIGO_POSTAL);
    }


    @Test
    @Transactional
    public void getAllCDireccionsByCiudadIsEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where ciudad equals to DEFAULT_CIUDAD
        defaultCDireccionShouldBeFound("ciudad.equals=" + DEFAULT_CIUDAD);

        // Get all the cDireccionList where ciudad equals to UPDATED_CIUDAD
        defaultCDireccionShouldNotBeFound("ciudad.equals=" + UPDATED_CIUDAD);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByCiudadIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where ciudad not equals to DEFAULT_CIUDAD
        defaultCDireccionShouldNotBeFound("ciudad.notEquals=" + DEFAULT_CIUDAD);

        // Get all the cDireccionList where ciudad not equals to UPDATED_CIUDAD
        defaultCDireccionShouldBeFound("ciudad.notEquals=" + UPDATED_CIUDAD);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByCiudadIsInShouldWork() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where ciudad in DEFAULT_CIUDAD or UPDATED_CIUDAD
        defaultCDireccionShouldBeFound("ciudad.in=" + DEFAULT_CIUDAD + "," + UPDATED_CIUDAD);

        // Get all the cDireccionList where ciudad equals to UPDATED_CIUDAD
        defaultCDireccionShouldNotBeFound("ciudad.in=" + UPDATED_CIUDAD);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByCiudadIsNullOrNotNull() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where ciudad is not null
        defaultCDireccionShouldBeFound("ciudad.specified=true");

        // Get all the cDireccionList where ciudad is null
        defaultCDireccionShouldNotBeFound("ciudad.specified=false");
    }
                @Test
    @Transactional
    public void getAllCDireccionsByCiudadContainsSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where ciudad contains DEFAULT_CIUDAD
        defaultCDireccionShouldBeFound("ciudad.contains=" + DEFAULT_CIUDAD);

        // Get all the cDireccionList where ciudad contains UPDATED_CIUDAD
        defaultCDireccionShouldNotBeFound("ciudad.contains=" + UPDATED_CIUDAD);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByCiudadNotContainsSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where ciudad does not contain DEFAULT_CIUDAD
        defaultCDireccionShouldNotBeFound("ciudad.doesNotContain=" + DEFAULT_CIUDAD);

        // Get all the cDireccionList where ciudad does not contain UPDATED_CIUDAD
        defaultCDireccionShouldBeFound("ciudad.doesNotContain=" + UPDATED_CIUDAD);
    }


    @Test
    @Transactional
    public void getAllCDireccionsByEstadoIsEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where estado equals to DEFAULT_ESTADO
        defaultCDireccionShouldBeFound("estado.equals=" + DEFAULT_ESTADO);

        // Get all the cDireccionList where estado equals to UPDATED_ESTADO
        defaultCDireccionShouldNotBeFound("estado.equals=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByEstadoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where estado not equals to DEFAULT_ESTADO
        defaultCDireccionShouldNotBeFound("estado.notEquals=" + DEFAULT_ESTADO);

        // Get all the cDireccionList where estado not equals to UPDATED_ESTADO
        defaultCDireccionShouldBeFound("estado.notEquals=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByEstadoIsInShouldWork() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where estado in DEFAULT_ESTADO or UPDATED_ESTADO
        defaultCDireccionShouldBeFound("estado.in=" + DEFAULT_ESTADO + "," + UPDATED_ESTADO);

        // Get all the cDireccionList where estado equals to UPDATED_ESTADO
        defaultCDireccionShouldNotBeFound("estado.in=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByEstadoIsNullOrNotNull() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where estado is not null
        defaultCDireccionShouldBeFound("estado.specified=true");

        // Get all the cDireccionList where estado is null
        defaultCDireccionShouldNotBeFound("estado.specified=false");
    }
                @Test
    @Transactional
    public void getAllCDireccionsByEstadoContainsSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where estado contains DEFAULT_ESTADO
        defaultCDireccionShouldBeFound("estado.contains=" + DEFAULT_ESTADO);

        // Get all the cDireccionList where estado contains UPDATED_ESTADO
        defaultCDireccionShouldNotBeFound("estado.contains=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByEstadoNotContainsSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where estado does not contain DEFAULT_ESTADO
        defaultCDireccionShouldNotBeFound("estado.doesNotContain=" + DEFAULT_ESTADO);

        // Get all the cDireccionList where estado does not contain UPDATED_ESTADO
        defaultCDireccionShouldBeFound("estado.doesNotContain=" + UPDATED_ESTADO);
    }


    @Test
    @Transactional
    public void getAllCDireccionsByPaisIsEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where pais equals to DEFAULT_PAIS
        defaultCDireccionShouldBeFound("pais.equals=" + DEFAULT_PAIS);

        // Get all the cDireccionList where pais equals to UPDATED_PAIS
        defaultCDireccionShouldNotBeFound("pais.equals=" + UPDATED_PAIS);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByPaisIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where pais not equals to DEFAULT_PAIS
        defaultCDireccionShouldNotBeFound("pais.notEquals=" + DEFAULT_PAIS);

        // Get all the cDireccionList where pais not equals to UPDATED_PAIS
        defaultCDireccionShouldBeFound("pais.notEquals=" + UPDATED_PAIS);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByPaisIsInShouldWork() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where pais in DEFAULT_PAIS or UPDATED_PAIS
        defaultCDireccionShouldBeFound("pais.in=" + DEFAULT_PAIS + "," + UPDATED_PAIS);

        // Get all the cDireccionList where pais equals to UPDATED_PAIS
        defaultCDireccionShouldNotBeFound("pais.in=" + UPDATED_PAIS);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByPaisIsNullOrNotNull() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where pais is not null
        defaultCDireccionShouldBeFound("pais.specified=true");

        // Get all the cDireccionList where pais is null
        defaultCDireccionShouldNotBeFound("pais.specified=false");
    }
                @Test
    @Transactional
    public void getAllCDireccionsByPaisContainsSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where pais contains DEFAULT_PAIS
        defaultCDireccionShouldBeFound("pais.contains=" + DEFAULT_PAIS);

        // Get all the cDireccionList where pais contains UPDATED_PAIS
        defaultCDireccionShouldNotBeFound("pais.contains=" + UPDATED_PAIS);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByPaisNotContainsSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where pais does not contain DEFAULT_PAIS
        defaultCDireccionShouldNotBeFound("pais.doesNotContain=" + DEFAULT_PAIS);

        // Get all the cDireccionList where pais does not contain UPDATED_PAIS
        defaultCDireccionShouldBeFound("pais.doesNotContain=" + UPDATED_PAIS);
    }


    @Test
    @Transactional
    public void getAllCDireccionsByIdUsuarioCreacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where idUsuarioCreacion equals to DEFAULT_ID_USUARIO_CREACION
        defaultCDireccionShouldBeFound("idUsuarioCreacion.equals=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cDireccionList where idUsuarioCreacion equals to UPDATED_ID_USUARIO_CREACION
        defaultCDireccionShouldNotBeFound("idUsuarioCreacion.equals=" + UPDATED_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByIdUsuarioCreacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where idUsuarioCreacion not equals to DEFAULT_ID_USUARIO_CREACION
        defaultCDireccionShouldNotBeFound("idUsuarioCreacion.notEquals=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cDireccionList where idUsuarioCreacion not equals to UPDATED_ID_USUARIO_CREACION
        defaultCDireccionShouldBeFound("idUsuarioCreacion.notEquals=" + UPDATED_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByIdUsuarioCreacionIsInShouldWork() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where idUsuarioCreacion in DEFAULT_ID_USUARIO_CREACION or UPDATED_ID_USUARIO_CREACION
        defaultCDireccionShouldBeFound("idUsuarioCreacion.in=" + DEFAULT_ID_USUARIO_CREACION + "," + UPDATED_ID_USUARIO_CREACION);

        // Get all the cDireccionList where idUsuarioCreacion equals to UPDATED_ID_USUARIO_CREACION
        defaultCDireccionShouldNotBeFound("idUsuarioCreacion.in=" + UPDATED_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByIdUsuarioCreacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where idUsuarioCreacion is not null
        defaultCDireccionShouldBeFound("idUsuarioCreacion.specified=true");

        // Get all the cDireccionList where idUsuarioCreacion is null
        defaultCDireccionShouldNotBeFound("idUsuarioCreacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCDireccionsByIdUsuarioCreacionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where idUsuarioCreacion is greater than or equal to DEFAULT_ID_USUARIO_CREACION
        defaultCDireccionShouldBeFound("idUsuarioCreacion.greaterThanOrEqual=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cDireccionList where idUsuarioCreacion is greater than or equal to (DEFAULT_ID_USUARIO_CREACION + 1)
        defaultCDireccionShouldNotBeFound("idUsuarioCreacion.greaterThanOrEqual=" + (DEFAULT_ID_USUARIO_CREACION + 1));
    }

    @Test
    @Transactional
    public void getAllCDireccionsByIdUsuarioCreacionIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where idUsuarioCreacion is less than or equal to DEFAULT_ID_USUARIO_CREACION
        defaultCDireccionShouldBeFound("idUsuarioCreacion.lessThanOrEqual=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cDireccionList where idUsuarioCreacion is less than or equal to SMALLER_ID_USUARIO_CREACION
        defaultCDireccionShouldNotBeFound("idUsuarioCreacion.lessThanOrEqual=" + SMALLER_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByIdUsuarioCreacionIsLessThanSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where idUsuarioCreacion is less than DEFAULT_ID_USUARIO_CREACION
        defaultCDireccionShouldNotBeFound("idUsuarioCreacion.lessThan=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cDireccionList where idUsuarioCreacion is less than (DEFAULT_ID_USUARIO_CREACION + 1)
        defaultCDireccionShouldBeFound("idUsuarioCreacion.lessThan=" + (DEFAULT_ID_USUARIO_CREACION + 1));
    }

    @Test
    @Transactional
    public void getAllCDireccionsByIdUsuarioCreacionIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where idUsuarioCreacion is greater than DEFAULT_ID_USUARIO_CREACION
        defaultCDireccionShouldNotBeFound("idUsuarioCreacion.greaterThan=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cDireccionList where idUsuarioCreacion is greater than SMALLER_ID_USUARIO_CREACION
        defaultCDireccionShouldBeFound("idUsuarioCreacion.greaterThan=" + SMALLER_ID_USUARIO_CREACION);
    }


    @Test
    @Transactional
    public void getAllCDireccionsByFechaCreacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where fechaCreacion equals to DEFAULT_FECHA_CREACION
        defaultCDireccionShouldBeFound("fechaCreacion.equals=" + DEFAULT_FECHA_CREACION);

        // Get all the cDireccionList where fechaCreacion equals to UPDATED_FECHA_CREACION
        defaultCDireccionShouldNotBeFound("fechaCreacion.equals=" + UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByFechaCreacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where fechaCreacion not equals to DEFAULT_FECHA_CREACION
        defaultCDireccionShouldNotBeFound("fechaCreacion.notEquals=" + DEFAULT_FECHA_CREACION);

        // Get all the cDireccionList where fechaCreacion not equals to UPDATED_FECHA_CREACION
        defaultCDireccionShouldBeFound("fechaCreacion.notEquals=" + UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByFechaCreacionIsInShouldWork() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where fechaCreacion in DEFAULT_FECHA_CREACION or UPDATED_FECHA_CREACION
        defaultCDireccionShouldBeFound("fechaCreacion.in=" + DEFAULT_FECHA_CREACION + "," + UPDATED_FECHA_CREACION);

        // Get all the cDireccionList where fechaCreacion equals to UPDATED_FECHA_CREACION
        defaultCDireccionShouldNotBeFound("fechaCreacion.in=" + UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByFechaCreacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where fechaCreacion is not null
        defaultCDireccionShouldBeFound("fechaCreacion.specified=true");

        // Get all the cDireccionList where fechaCreacion is null
        defaultCDireccionShouldNotBeFound("fechaCreacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCDireccionsByIdUsuarioActualizacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where idUsuarioActualizacion equals to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCDireccionShouldBeFound("idUsuarioActualizacion.equals=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cDireccionList where idUsuarioActualizacion equals to UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCDireccionShouldNotBeFound("idUsuarioActualizacion.equals=" + UPDATED_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByIdUsuarioActualizacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where idUsuarioActualizacion not equals to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCDireccionShouldNotBeFound("idUsuarioActualizacion.notEquals=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cDireccionList where idUsuarioActualizacion not equals to UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCDireccionShouldBeFound("idUsuarioActualizacion.notEquals=" + UPDATED_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByIdUsuarioActualizacionIsInShouldWork() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where idUsuarioActualizacion in DEFAULT_ID_USUARIO_ACTUALIZACION or UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCDireccionShouldBeFound("idUsuarioActualizacion.in=" + DEFAULT_ID_USUARIO_ACTUALIZACION + "," + UPDATED_ID_USUARIO_ACTUALIZACION);

        // Get all the cDireccionList where idUsuarioActualizacion equals to UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCDireccionShouldNotBeFound("idUsuarioActualizacion.in=" + UPDATED_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByIdUsuarioActualizacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where idUsuarioActualizacion is not null
        defaultCDireccionShouldBeFound("idUsuarioActualizacion.specified=true");

        // Get all the cDireccionList where idUsuarioActualizacion is null
        defaultCDireccionShouldNotBeFound("idUsuarioActualizacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCDireccionsByIdUsuarioActualizacionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where idUsuarioActualizacion is greater than or equal to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCDireccionShouldBeFound("idUsuarioActualizacion.greaterThanOrEqual=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cDireccionList where idUsuarioActualizacion is greater than or equal to (DEFAULT_ID_USUARIO_ACTUALIZACION + 1)
        defaultCDireccionShouldNotBeFound("idUsuarioActualizacion.greaterThanOrEqual=" + (DEFAULT_ID_USUARIO_ACTUALIZACION + 1));
    }

    @Test
    @Transactional
    public void getAllCDireccionsByIdUsuarioActualizacionIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where idUsuarioActualizacion is less than or equal to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCDireccionShouldBeFound("idUsuarioActualizacion.lessThanOrEqual=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cDireccionList where idUsuarioActualizacion is less than or equal to SMALLER_ID_USUARIO_ACTUALIZACION
        defaultCDireccionShouldNotBeFound("idUsuarioActualizacion.lessThanOrEqual=" + SMALLER_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByIdUsuarioActualizacionIsLessThanSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where idUsuarioActualizacion is less than DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCDireccionShouldNotBeFound("idUsuarioActualizacion.lessThan=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cDireccionList where idUsuarioActualizacion is less than (DEFAULT_ID_USUARIO_ACTUALIZACION + 1)
        defaultCDireccionShouldBeFound("idUsuarioActualizacion.lessThan=" + (DEFAULT_ID_USUARIO_ACTUALIZACION + 1));
    }

    @Test
    @Transactional
    public void getAllCDireccionsByIdUsuarioActualizacionIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where idUsuarioActualizacion is greater than DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCDireccionShouldNotBeFound("idUsuarioActualizacion.greaterThan=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cDireccionList where idUsuarioActualizacion is greater than SMALLER_ID_USUARIO_ACTUALIZACION
        defaultCDireccionShouldBeFound("idUsuarioActualizacion.greaterThan=" + SMALLER_ID_USUARIO_ACTUALIZACION);
    }


    @Test
    @Transactional
    public void getAllCDireccionsByFechaActualizacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where fechaActualizacion equals to DEFAULT_FECHA_ACTUALIZACION
        defaultCDireccionShouldBeFound("fechaActualizacion.equals=" + DEFAULT_FECHA_ACTUALIZACION);

        // Get all the cDireccionList where fechaActualizacion equals to UPDATED_FECHA_ACTUALIZACION
        defaultCDireccionShouldNotBeFound("fechaActualizacion.equals=" + UPDATED_FECHA_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByFechaActualizacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where fechaActualizacion not equals to DEFAULT_FECHA_ACTUALIZACION
        defaultCDireccionShouldNotBeFound("fechaActualizacion.notEquals=" + DEFAULT_FECHA_ACTUALIZACION);

        // Get all the cDireccionList where fechaActualizacion not equals to UPDATED_FECHA_ACTUALIZACION
        defaultCDireccionShouldBeFound("fechaActualizacion.notEquals=" + UPDATED_FECHA_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByFechaActualizacionIsInShouldWork() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where fechaActualizacion in DEFAULT_FECHA_ACTUALIZACION or UPDATED_FECHA_ACTUALIZACION
        defaultCDireccionShouldBeFound("fechaActualizacion.in=" + DEFAULT_FECHA_ACTUALIZACION + "," + UPDATED_FECHA_ACTUALIZACION);

        // Get all the cDireccionList where fechaActualizacion equals to UPDATED_FECHA_ACTUALIZACION
        defaultCDireccionShouldNotBeFound("fechaActualizacion.in=" + UPDATED_FECHA_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByFechaActualizacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where fechaActualizacion is not null
        defaultCDireccionShouldBeFound("fechaActualizacion.specified=true");

        // Get all the cDireccionList where fechaActualizacion is null
        defaultCDireccionShouldNotBeFound("fechaActualizacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCDireccionsByNotasIsEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where notas equals to DEFAULT_NOTAS
        defaultCDireccionShouldBeFound("notas.equals=" + DEFAULT_NOTAS);

        // Get all the cDireccionList where notas equals to UPDATED_NOTAS
        defaultCDireccionShouldNotBeFound("notas.equals=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByNotasIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where notas not equals to DEFAULT_NOTAS
        defaultCDireccionShouldNotBeFound("notas.notEquals=" + DEFAULT_NOTAS);

        // Get all the cDireccionList where notas not equals to UPDATED_NOTAS
        defaultCDireccionShouldBeFound("notas.notEquals=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByNotasIsInShouldWork() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where notas in DEFAULT_NOTAS or UPDATED_NOTAS
        defaultCDireccionShouldBeFound("notas.in=" + DEFAULT_NOTAS + "," + UPDATED_NOTAS);

        // Get all the cDireccionList where notas equals to UPDATED_NOTAS
        defaultCDireccionShouldNotBeFound("notas.in=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByNotasIsNullOrNotNull() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where notas is not null
        defaultCDireccionShouldBeFound("notas.specified=true");

        // Get all the cDireccionList where notas is null
        defaultCDireccionShouldNotBeFound("notas.specified=false");
    }
                @Test
    @Transactional
    public void getAllCDireccionsByNotasContainsSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where notas contains DEFAULT_NOTAS
        defaultCDireccionShouldBeFound("notas.contains=" + DEFAULT_NOTAS);

        // Get all the cDireccionList where notas contains UPDATED_NOTAS
        defaultCDireccionShouldNotBeFound("notas.contains=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByNotasNotContainsSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where notas does not contain DEFAULT_NOTAS
        defaultCDireccionShouldNotBeFound("notas.doesNotContain=" + DEFAULT_NOTAS);

        // Get all the cDireccionList where notas does not contain UPDATED_NOTAS
        defaultCDireccionShouldBeFound("notas.doesNotContain=" + UPDATED_NOTAS);
    }


    @Test
    @Transactional
    public void getAllCDireccionsByEstatusIsEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where estatus equals to DEFAULT_ESTATUS
        defaultCDireccionShouldBeFound("estatus.equals=" + DEFAULT_ESTATUS);

        // Get all the cDireccionList where estatus equals to UPDATED_ESTATUS
        defaultCDireccionShouldNotBeFound("estatus.equals=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByEstatusIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where estatus not equals to DEFAULT_ESTATUS
        defaultCDireccionShouldNotBeFound("estatus.notEquals=" + DEFAULT_ESTATUS);

        // Get all the cDireccionList where estatus not equals to UPDATED_ESTATUS
        defaultCDireccionShouldBeFound("estatus.notEquals=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByEstatusIsInShouldWork() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where estatus in DEFAULT_ESTATUS or UPDATED_ESTATUS
        defaultCDireccionShouldBeFound("estatus.in=" + DEFAULT_ESTATUS + "," + UPDATED_ESTATUS);

        // Get all the cDireccionList where estatus equals to UPDATED_ESTATUS
        defaultCDireccionShouldNotBeFound("estatus.in=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByEstatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where estatus is not null
        defaultCDireccionShouldBeFound("estatus.specified=true");

        // Get all the cDireccionList where estatus is null
        defaultCDireccionShouldNotBeFound("estatus.specified=false");
    }

    @Test
    @Transactional
    public void getAllCDireccionsByEstatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where estatus is greater than or equal to DEFAULT_ESTATUS
        defaultCDireccionShouldBeFound("estatus.greaterThanOrEqual=" + DEFAULT_ESTATUS);

        // Get all the cDireccionList where estatus is greater than or equal to (DEFAULT_ESTATUS + 1)
        defaultCDireccionShouldNotBeFound("estatus.greaterThanOrEqual=" + (DEFAULT_ESTATUS + 1));
    }

    @Test
    @Transactional
    public void getAllCDireccionsByEstatusIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where estatus is less than or equal to DEFAULT_ESTATUS
        defaultCDireccionShouldBeFound("estatus.lessThanOrEqual=" + DEFAULT_ESTATUS);

        // Get all the cDireccionList where estatus is less than or equal to SMALLER_ESTATUS
        defaultCDireccionShouldNotBeFound("estatus.lessThanOrEqual=" + SMALLER_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByEstatusIsLessThanSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where estatus is less than DEFAULT_ESTATUS
        defaultCDireccionShouldNotBeFound("estatus.lessThan=" + DEFAULT_ESTATUS);

        // Get all the cDireccionList where estatus is less than (DEFAULT_ESTATUS + 1)
        defaultCDireccionShouldBeFound("estatus.lessThan=" + (DEFAULT_ESTATUS + 1));
    }

    @Test
    @Transactional
    public void getAllCDireccionsByEstatusIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where estatus is greater than DEFAULT_ESTATUS
        defaultCDireccionShouldNotBeFound("estatus.greaterThan=" + DEFAULT_ESTATUS);

        // Get all the cDireccionList where estatus is greater than SMALLER_ESTATUS
        defaultCDireccionShouldBeFound("estatus.greaterThan=" + SMALLER_ESTATUS);
    }


    @Test
    @Transactional
    public void getAllCDireccionsByBorradoIsEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where borrado equals to DEFAULT_BORRADO
        defaultCDireccionShouldBeFound("borrado.equals=" + DEFAULT_BORRADO);

        // Get all the cDireccionList where borrado equals to UPDATED_BORRADO
        defaultCDireccionShouldNotBeFound("borrado.equals=" + UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByBorradoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where borrado not equals to DEFAULT_BORRADO
        defaultCDireccionShouldNotBeFound("borrado.notEquals=" + DEFAULT_BORRADO);

        // Get all the cDireccionList where borrado not equals to UPDATED_BORRADO
        defaultCDireccionShouldBeFound("borrado.notEquals=" + UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByBorradoIsInShouldWork() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where borrado in DEFAULT_BORRADO or UPDATED_BORRADO
        defaultCDireccionShouldBeFound("borrado.in=" + DEFAULT_BORRADO + "," + UPDATED_BORRADO);

        // Get all the cDireccionList where borrado equals to UPDATED_BORRADO
        defaultCDireccionShouldNotBeFound("borrado.in=" + UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByBorradoIsNullOrNotNull() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where borrado is not null
        defaultCDireccionShouldBeFound("borrado.specified=true");

        // Get all the cDireccionList where borrado is null
        defaultCDireccionShouldNotBeFound("borrado.specified=false");
    }

    @Test
    @Transactional
    public void getAllCDireccionsByBorradoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where borrado is greater than or equal to DEFAULT_BORRADO
        defaultCDireccionShouldBeFound("borrado.greaterThanOrEqual=" + DEFAULT_BORRADO);

        // Get all the cDireccionList where borrado is greater than or equal to (DEFAULT_BORRADO + 1)
        defaultCDireccionShouldNotBeFound("borrado.greaterThanOrEqual=" + (DEFAULT_BORRADO + 1));
    }

    @Test
    @Transactional
    public void getAllCDireccionsByBorradoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where borrado is less than or equal to DEFAULT_BORRADO
        defaultCDireccionShouldBeFound("borrado.lessThanOrEqual=" + DEFAULT_BORRADO);

        // Get all the cDireccionList where borrado is less than or equal to SMALLER_BORRADO
        defaultCDireccionShouldNotBeFound("borrado.lessThanOrEqual=" + SMALLER_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCDireccionsByBorradoIsLessThanSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where borrado is less than DEFAULT_BORRADO
        defaultCDireccionShouldNotBeFound("borrado.lessThan=" + DEFAULT_BORRADO);

        // Get all the cDireccionList where borrado is less than (DEFAULT_BORRADO + 1)
        defaultCDireccionShouldBeFound("borrado.lessThan=" + (DEFAULT_BORRADO + 1));
    }

    @Test
    @Transactional
    public void getAllCDireccionsByBorradoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        // Get all the cDireccionList where borrado is greater than DEFAULT_BORRADO
        defaultCDireccionShouldNotBeFound("borrado.greaterThan=" + DEFAULT_BORRADO);

        // Get all the cDireccionList where borrado is greater than SMALLER_BORRADO
        defaultCDireccionShouldBeFound("borrado.greaterThan=" + SMALLER_BORRADO);
    }


    @Test
    @Transactional
    public void getAllCDireccionsByClienteIsEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);
        CCliente cliente = CClienteResourceIT.createEntity(em);
        em.persist(cliente);
        em.flush();
        cDireccion.setCliente(cliente);
        cDireccionRepository.saveAndFlush(cDireccion);
        Long clienteId = cliente.getId();

        // Get all the cDireccionList where cliente equals to clienteId
        defaultCDireccionShouldBeFound("clienteId.equals=" + clienteId);

        // Get all the cDireccionList where cliente equals to clienteId + 1
        defaultCDireccionShouldNotBeFound("clienteId.equals=" + (clienteId + 1));
    }


    @Test
    @Transactional
    public void getAllCDireccionsByPasajeroIsEqualToSomething() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);
        CPasajero pasajero = CPasajeroResourceIT.createEntity(em);
        em.persist(pasajero);
        em.flush();
        cDireccion.setPasajero(pasajero);
        cDireccionRepository.saveAndFlush(cDireccion);
        Long pasajeroId = pasajero.getId();

        // Get all the cDireccionList where pasajero equals to pasajeroId
        defaultCDireccionShouldBeFound("pasajeroId.equals=" + pasajeroId);

        // Get all the cDireccionList where pasajero equals to pasajeroId + 1
        defaultCDireccionShouldNotBeFound("pasajeroId.equals=" + (pasajeroId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCDireccionShouldBeFound(String filter) throws Exception {
        restCDireccionMockMvc.perform(get("/api/c-direccions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cDireccion.getId().intValue())))
            .andExpect(jsonPath("$.[*].direccionComplete").value(hasItem(DEFAULT_DIRECCION_COMPLETE)))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION)))
            .andExpect(jsonPath("$.[*].numExterior").value(hasItem(DEFAULT_NUM_EXTERIOR)))
            .andExpect(jsonPath("$.[*].numInterior").value(hasItem(DEFAULT_NUM_INTERIOR)))
            .andExpect(jsonPath("$.[*].codigoPostal").value(hasItem(DEFAULT_CODIGO_POSTAL)))
            .andExpect(jsonPath("$.[*].ciudad").value(hasItem(DEFAULT_CIUDAD)))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO)))
            .andExpect(jsonPath("$.[*].pais").value(hasItem(DEFAULT_PAIS)))
            .andExpect(jsonPath("$.[*].idUsuarioCreacion").value(hasItem(DEFAULT_ID_USUARIO_CREACION.intValue())))
            .andExpect(jsonPath("$.[*].fechaCreacion").value(hasItem(DEFAULT_FECHA_CREACION.toString())))
            .andExpect(jsonPath("$.[*].idUsuarioActualizacion").value(hasItem(DEFAULT_ID_USUARIO_ACTUALIZACION.intValue())))
            .andExpect(jsonPath("$.[*].fechaActualizacion").value(hasItem(DEFAULT_FECHA_ACTUALIZACION.toString())))
            .andExpect(jsonPath("$.[*].notas").value(hasItem(DEFAULT_NOTAS)))
            .andExpect(jsonPath("$.[*].estatus").value(hasItem(DEFAULT_ESTATUS)))
            .andExpect(jsonPath("$.[*].borrado").value(hasItem(DEFAULT_BORRADO)));

        // Check, that the count call also returns 1
        restCDireccionMockMvc.perform(get("/api/c-direccions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCDireccionShouldNotBeFound(String filter) throws Exception {
        restCDireccionMockMvc.perform(get("/api/c-direccions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCDireccionMockMvc.perform(get("/api/c-direccions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCDireccion() throws Exception {
        // Get the cDireccion
        restCDireccionMockMvc.perform(get("/api/c-direccions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCDireccion() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        int databaseSizeBeforeUpdate = cDireccionRepository.findAll().size();

        // Update the cDireccion
        CDireccion updatedCDireccion = cDireccionRepository.findById(cDireccion.getId()).get();
        // Disconnect from session so that the updates on updatedCDireccion are not directly saved in db
        em.detach(updatedCDireccion);
        updatedCDireccion
            .direccionComplete(UPDATED_DIRECCION_COMPLETE)
            .direccion(UPDATED_DIRECCION)
            .numExterior(UPDATED_NUM_EXTERIOR)
            .numInterior(UPDATED_NUM_INTERIOR)
            .codigoPostal(UPDATED_CODIGO_POSTAL)
            .ciudad(UPDATED_CIUDAD)
            .estado(UPDATED_ESTADO)
            .pais(UPDATED_PAIS)
            .idUsuarioCreacion(UPDATED_ID_USUARIO_CREACION)
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .idUsuarioActualizacion(UPDATED_ID_USUARIO_ACTUALIZACION)
            .fechaActualizacion(UPDATED_FECHA_ACTUALIZACION)
            .notas(UPDATED_NOTAS)
            .estatus(UPDATED_ESTATUS)
            .borrado(UPDATED_BORRADO);
        CDireccionDTO cDireccionDTO = cDireccionMapper.toDto(updatedCDireccion);

        restCDireccionMockMvc.perform(put("/api/c-direccions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cDireccionDTO)))
            .andExpect(status().isOk());

        // Validate the CDireccion in the database
        List<CDireccion> cDireccionList = cDireccionRepository.findAll();
        assertThat(cDireccionList).hasSize(databaseSizeBeforeUpdate);
        CDireccion testCDireccion = cDireccionList.get(cDireccionList.size() - 1);
        assertThat(testCDireccion.getDireccionComplete()).isEqualTo(UPDATED_DIRECCION_COMPLETE);
        assertThat(testCDireccion.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testCDireccion.getNumExterior()).isEqualTo(UPDATED_NUM_EXTERIOR);
        assertThat(testCDireccion.getNumInterior()).isEqualTo(UPDATED_NUM_INTERIOR);
        assertThat(testCDireccion.getCodigoPostal()).isEqualTo(UPDATED_CODIGO_POSTAL);
        assertThat(testCDireccion.getCiudad()).isEqualTo(UPDATED_CIUDAD);
        assertThat(testCDireccion.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testCDireccion.getPais()).isEqualTo(UPDATED_PAIS);
        assertThat(testCDireccion.getIdUsuarioCreacion()).isEqualTo(UPDATED_ID_USUARIO_CREACION);
        assertThat(testCDireccion.getFechaCreacion()).isEqualTo(UPDATED_FECHA_CREACION);
        assertThat(testCDireccion.getIdUsuarioActualizacion()).isEqualTo(UPDATED_ID_USUARIO_ACTUALIZACION);
        assertThat(testCDireccion.getFechaActualizacion()).isEqualTo(UPDATED_FECHA_ACTUALIZACION);
        assertThat(testCDireccion.getNotas()).isEqualTo(UPDATED_NOTAS);
        assertThat(testCDireccion.getEstatus()).isEqualTo(UPDATED_ESTATUS);
        assertThat(testCDireccion.getBorrado()).isEqualTo(UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void updateNonExistingCDireccion() throws Exception {
        int databaseSizeBeforeUpdate = cDireccionRepository.findAll().size();

        // Create the CDireccion
        CDireccionDTO cDireccionDTO = cDireccionMapper.toDto(cDireccion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCDireccionMockMvc.perform(put("/api/c-direccions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cDireccionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CDireccion in the database
        List<CDireccion> cDireccionList = cDireccionRepository.findAll();
        assertThat(cDireccionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCDireccion() throws Exception {
        // Initialize the database
        cDireccionRepository.saveAndFlush(cDireccion);

        int databaseSizeBeforeDelete = cDireccionRepository.findAll().size();

        // Delete the cDireccion
        restCDireccionMockMvc.perform(delete("/api/c-direccions/{id}", cDireccion.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CDireccion> cDireccionList = cDireccionRepository.findAll();
        assertThat(cDireccionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
