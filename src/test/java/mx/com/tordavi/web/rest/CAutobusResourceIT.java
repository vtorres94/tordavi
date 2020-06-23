package mx.com.tordavi.web.rest;

import mx.com.tordavi.TordaviApp;
import mx.com.tordavi.domain.CAutobus;
import mx.com.tordavi.domain.CCliente;
import mx.com.tordavi.repository.CAutobusRepository;
import mx.com.tordavi.service.CAutobusService;
import mx.com.tordavi.service.dto.CAutobusDTO;
import mx.com.tordavi.service.mapper.CAutobusMapper;
import mx.com.tordavi.service.dto.CAutobusCriteria;
import mx.com.tordavi.service.CAutobusQueryService;

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
 * Integration tests for the {@link CAutobusResource} REST controller.
 */
@SpringBootTest(classes = TordaviApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class CAutobusResourceIT {

    private static final String DEFAULT_AUTOBUS = "AAAAAAAAAA";
    private static final String UPDATED_AUTOBUS = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_USUARIO_CREACION = 9999999999L;
    private static final Long UPDATED_ID_USUARIO_CREACION = 9999999998L;
    private static final Long SMALLER_ID_USUARIO_CREACION = 9999999999L - 1L;

    private static final Instant DEFAULT_FECHA_CREACION = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FECHA_CREACION = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_ID_USUARIO_ACTUALIZACION = 9999999999L;
    private static final Long UPDATED_ID_USUARIO_ACTUALIZACION = 9999999998L;
    private static final Long SMALLER_ID_USUARIO_ACTUALIZACION = 9999999999L - 1L;

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
    private CAutobusRepository cAutobusRepository;

    @Autowired
    private CAutobusMapper cAutobusMapper;

    @Autowired
    private CAutobusService cAutobusService;

    @Autowired
    private CAutobusQueryService cAutobusQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCAutobusMockMvc;

    private CAutobus cAutobus;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CAutobus createEntity(EntityManager em) {
        CAutobus cAutobus = new CAutobus()
            .autobus(DEFAULT_AUTOBUS)
            .idUsuarioCreacion(DEFAULT_ID_USUARIO_CREACION)
            .fechaCreacion(DEFAULT_FECHA_CREACION)
            .idUsuarioActualizacion(DEFAULT_ID_USUARIO_ACTUALIZACION)
            .fechaActualizacion(DEFAULT_FECHA_ACTUALIZACION)
            .notas(DEFAULT_NOTAS)
            .estatus(DEFAULT_ESTATUS)
            .borrado(DEFAULT_BORRADO);
        return cAutobus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CAutobus createUpdatedEntity(EntityManager em) {
        CAutobus cAutobus = new CAutobus()
            .autobus(UPDATED_AUTOBUS)
            .idUsuarioCreacion(UPDATED_ID_USUARIO_CREACION)
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .idUsuarioActualizacion(UPDATED_ID_USUARIO_ACTUALIZACION)
            .fechaActualizacion(UPDATED_FECHA_ACTUALIZACION)
            .notas(UPDATED_NOTAS)
            .estatus(UPDATED_ESTATUS)
            .borrado(UPDATED_BORRADO);
        return cAutobus;
    }

    @BeforeEach
    public void initTest() {
        cAutobus = createEntity(em);
    }

    @Test
    @Transactional
    public void createCAutobus() throws Exception {
        int databaseSizeBeforeCreate = cAutobusRepository.findAll().size();

        // Create the CAutobus
        CAutobusDTO cAutobusDTO = cAutobusMapper.toDto(cAutobus);
        restCAutobusMockMvc.perform(post("/api/c-autobuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cAutobusDTO)))
            .andExpect(status().isCreated());

        // Validate the CAutobus in the database
        List<CAutobus> cAutobusList = cAutobusRepository.findAll();
        assertThat(cAutobusList).hasSize(databaseSizeBeforeCreate + 1);
        CAutobus testCAutobus = cAutobusList.get(cAutobusList.size() - 1);
        assertThat(testCAutobus.getAutobus()).isEqualTo(DEFAULT_AUTOBUS);
        assertThat(testCAutobus.getIdUsuarioCreacion()).isEqualTo(DEFAULT_ID_USUARIO_CREACION);
        assertThat(testCAutobus.getFechaCreacion()).isEqualTo(DEFAULT_FECHA_CREACION);
        assertThat(testCAutobus.getIdUsuarioActualizacion()).isEqualTo(DEFAULT_ID_USUARIO_ACTUALIZACION);
        assertThat(testCAutobus.getFechaActualizacion()).isEqualTo(DEFAULT_FECHA_ACTUALIZACION);
        assertThat(testCAutobus.getNotas()).isEqualTo(DEFAULT_NOTAS);
        assertThat(testCAutobus.getEstatus()).isEqualTo(DEFAULT_ESTATUS);
        assertThat(testCAutobus.getBorrado()).isEqualTo(DEFAULT_BORRADO);
    }

    @Test
    @Transactional
    public void createCAutobusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cAutobusRepository.findAll().size();

        // Create the CAutobus with an existing ID
        cAutobus.setId(1L);
        CAutobusDTO cAutobusDTO = cAutobusMapper.toDto(cAutobus);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCAutobusMockMvc.perform(post("/api/c-autobuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cAutobusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CAutobus in the database
        List<CAutobus> cAutobusList = cAutobusRepository.findAll();
        assertThat(cAutobusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdUsuarioCreacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = cAutobusRepository.findAll().size();
        // set the field null
        cAutobus.setIdUsuarioCreacion(null);

        // Create the CAutobus, which fails.
        CAutobusDTO cAutobusDTO = cAutobusMapper.toDto(cAutobus);

        restCAutobusMockMvc.perform(post("/api/c-autobuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cAutobusDTO)))
            .andExpect(status().isBadRequest());

        List<CAutobus> cAutobusList = cAutobusRepository.findAll();
        assertThat(cAutobusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFechaCreacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = cAutobusRepository.findAll().size();
        // set the field null
        cAutobus.setFechaCreacion(null);

        // Create the CAutobus, which fails.
        CAutobusDTO cAutobusDTO = cAutobusMapper.toDto(cAutobus);

        restCAutobusMockMvc.perform(post("/api/c-autobuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cAutobusDTO)))
            .andExpect(status().isBadRequest());

        List<CAutobus> cAutobusList = cAutobusRepository.findAll();
        assertThat(cAutobusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = cAutobusRepository.findAll().size();
        // set the field null
        cAutobus.setEstatus(null);

        // Create the CAutobus, which fails.
        CAutobusDTO cAutobusDTO = cAutobusMapper.toDto(cAutobus);

        restCAutobusMockMvc.perform(post("/api/c-autobuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cAutobusDTO)))
            .andExpect(status().isBadRequest());

        List<CAutobus> cAutobusList = cAutobusRepository.findAll();
        assertThat(cAutobusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBorradoIsRequired() throws Exception {
        int databaseSizeBeforeTest = cAutobusRepository.findAll().size();
        // set the field null
        cAutobus.setBorrado(null);

        // Create the CAutobus, which fails.
        CAutobusDTO cAutobusDTO = cAutobusMapper.toDto(cAutobus);

        restCAutobusMockMvc.perform(post("/api/c-autobuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cAutobusDTO)))
            .andExpect(status().isBadRequest());

        List<CAutobus> cAutobusList = cAutobusRepository.findAll();
        assertThat(cAutobusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCAutobuses() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList
        restCAutobusMockMvc.perform(get("/api/c-autobuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cAutobus.getId().intValue())))
            .andExpect(jsonPath("$.[*].autobus").value(hasItem(DEFAULT_AUTOBUS)))
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
    public void getCAutobus() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get the cAutobus
        restCAutobusMockMvc.perform(get("/api/c-autobuses/{id}", cAutobus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cAutobus.getId().intValue()))
            .andExpect(jsonPath("$.autobus").value(DEFAULT_AUTOBUS))
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
    public void getCAutobusesByIdFiltering() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        Long id = cAutobus.getId();

        defaultCAutobusShouldBeFound("id.equals=" + id);
        defaultCAutobusShouldNotBeFound("id.notEquals=" + id);

        defaultCAutobusShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCAutobusShouldNotBeFound("id.greaterThan=" + id);

        defaultCAutobusShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCAutobusShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllCAutobusesByAutobusIsEqualToSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where autobus equals to DEFAULT_AUTOBUS
        defaultCAutobusShouldBeFound("autobus.equals=" + DEFAULT_AUTOBUS);

        // Get all the cAutobusList where autobus equals to UPDATED_AUTOBUS
        defaultCAutobusShouldNotBeFound("autobus.equals=" + UPDATED_AUTOBUS);
    }

    @Test
    @Transactional
    public void getAllCAutobusesByAutobusIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where autobus not equals to DEFAULT_AUTOBUS
        defaultCAutobusShouldNotBeFound("autobus.notEquals=" + DEFAULT_AUTOBUS);

        // Get all the cAutobusList where autobus not equals to UPDATED_AUTOBUS
        defaultCAutobusShouldBeFound("autobus.notEquals=" + UPDATED_AUTOBUS);
    }

    @Test
    @Transactional
    public void getAllCAutobusesByAutobusIsInShouldWork() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where autobus in DEFAULT_AUTOBUS or UPDATED_AUTOBUS
        defaultCAutobusShouldBeFound("autobus.in=" + DEFAULT_AUTOBUS + "," + UPDATED_AUTOBUS);

        // Get all the cAutobusList where autobus equals to UPDATED_AUTOBUS
        defaultCAutobusShouldNotBeFound("autobus.in=" + UPDATED_AUTOBUS);
    }

    @Test
    @Transactional
    public void getAllCAutobusesByAutobusIsNullOrNotNull() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where autobus is not null
        defaultCAutobusShouldBeFound("autobus.specified=true");

        // Get all the cAutobusList where autobus is null
        defaultCAutobusShouldNotBeFound("autobus.specified=false");
    }
                @Test
    @Transactional
    public void getAllCAutobusesByAutobusContainsSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where autobus contains DEFAULT_AUTOBUS
        defaultCAutobusShouldBeFound("autobus.contains=" + DEFAULT_AUTOBUS);

        // Get all the cAutobusList where autobus contains UPDATED_AUTOBUS
        defaultCAutobusShouldNotBeFound("autobus.contains=" + UPDATED_AUTOBUS);
    }

    @Test
    @Transactional
    public void getAllCAutobusesByAutobusNotContainsSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where autobus does not contain DEFAULT_AUTOBUS
        defaultCAutobusShouldNotBeFound("autobus.doesNotContain=" + DEFAULT_AUTOBUS);

        // Get all the cAutobusList where autobus does not contain UPDATED_AUTOBUS
        defaultCAutobusShouldBeFound("autobus.doesNotContain=" + UPDATED_AUTOBUS);
    }


    @Test
    @Transactional
    public void getAllCAutobusesByIdUsuarioCreacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where idUsuarioCreacion equals to DEFAULT_ID_USUARIO_CREACION
        defaultCAutobusShouldBeFound("idUsuarioCreacion.equals=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cAutobusList where idUsuarioCreacion equals to UPDATED_ID_USUARIO_CREACION
        defaultCAutobusShouldNotBeFound("idUsuarioCreacion.equals=" + UPDATED_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCAutobusesByIdUsuarioCreacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where idUsuarioCreacion not equals to DEFAULT_ID_USUARIO_CREACION
        defaultCAutobusShouldNotBeFound("idUsuarioCreacion.notEquals=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cAutobusList where idUsuarioCreacion not equals to UPDATED_ID_USUARIO_CREACION
        defaultCAutobusShouldBeFound("idUsuarioCreacion.notEquals=" + UPDATED_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCAutobusesByIdUsuarioCreacionIsInShouldWork() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where idUsuarioCreacion in DEFAULT_ID_USUARIO_CREACION or UPDATED_ID_USUARIO_CREACION
        defaultCAutobusShouldBeFound("idUsuarioCreacion.in=" + DEFAULT_ID_USUARIO_CREACION + "," + UPDATED_ID_USUARIO_CREACION);

        // Get all the cAutobusList where idUsuarioCreacion equals to UPDATED_ID_USUARIO_CREACION
        defaultCAutobusShouldNotBeFound("idUsuarioCreacion.in=" + UPDATED_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCAutobusesByIdUsuarioCreacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where idUsuarioCreacion is not null
        defaultCAutobusShouldBeFound("idUsuarioCreacion.specified=true");

        // Get all the cAutobusList where idUsuarioCreacion is null
        defaultCAutobusShouldNotBeFound("idUsuarioCreacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCAutobusesByIdUsuarioCreacionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where idUsuarioCreacion is greater than or equal to DEFAULT_ID_USUARIO_CREACION
        defaultCAutobusShouldBeFound("idUsuarioCreacion.greaterThanOrEqual=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cAutobusList where idUsuarioCreacion is greater than or equal to (DEFAULT_ID_USUARIO_CREACION + 1)
        defaultCAutobusShouldNotBeFound("idUsuarioCreacion.greaterThanOrEqual=" + (DEFAULT_ID_USUARIO_CREACION + 1));
    }

    @Test
    @Transactional
    public void getAllCAutobusesByIdUsuarioCreacionIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where idUsuarioCreacion is less than or equal to DEFAULT_ID_USUARIO_CREACION
        defaultCAutobusShouldBeFound("idUsuarioCreacion.lessThanOrEqual=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cAutobusList where idUsuarioCreacion is less than or equal to SMALLER_ID_USUARIO_CREACION
        defaultCAutobusShouldNotBeFound("idUsuarioCreacion.lessThanOrEqual=" + SMALLER_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCAutobusesByIdUsuarioCreacionIsLessThanSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where idUsuarioCreacion is less than DEFAULT_ID_USUARIO_CREACION
        defaultCAutobusShouldNotBeFound("idUsuarioCreacion.lessThan=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cAutobusList where idUsuarioCreacion is less than (DEFAULT_ID_USUARIO_CREACION + 1)
        defaultCAutobusShouldBeFound("idUsuarioCreacion.lessThan=" + (DEFAULT_ID_USUARIO_CREACION + 1));
    }

    @Test
    @Transactional
    public void getAllCAutobusesByIdUsuarioCreacionIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where idUsuarioCreacion is greater than DEFAULT_ID_USUARIO_CREACION
        defaultCAutobusShouldNotBeFound("idUsuarioCreacion.greaterThan=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cAutobusList where idUsuarioCreacion is greater than SMALLER_ID_USUARIO_CREACION
        defaultCAutobusShouldBeFound("idUsuarioCreacion.greaterThan=" + SMALLER_ID_USUARIO_CREACION);
    }


    @Test
    @Transactional
    public void getAllCAutobusesByFechaCreacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where fechaCreacion equals to DEFAULT_FECHA_CREACION
        defaultCAutobusShouldBeFound("fechaCreacion.equals=" + DEFAULT_FECHA_CREACION);

        // Get all the cAutobusList where fechaCreacion equals to UPDATED_FECHA_CREACION
        defaultCAutobusShouldNotBeFound("fechaCreacion.equals=" + UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void getAllCAutobusesByFechaCreacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where fechaCreacion not equals to DEFAULT_FECHA_CREACION
        defaultCAutobusShouldNotBeFound("fechaCreacion.notEquals=" + DEFAULT_FECHA_CREACION);

        // Get all the cAutobusList where fechaCreacion not equals to UPDATED_FECHA_CREACION
        defaultCAutobusShouldBeFound("fechaCreacion.notEquals=" + UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void getAllCAutobusesByFechaCreacionIsInShouldWork() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where fechaCreacion in DEFAULT_FECHA_CREACION or UPDATED_FECHA_CREACION
        defaultCAutobusShouldBeFound("fechaCreacion.in=" + DEFAULT_FECHA_CREACION + "," + UPDATED_FECHA_CREACION);

        // Get all the cAutobusList where fechaCreacion equals to UPDATED_FECHA_CREACION
        defaultCAutobusShouldNotBeFound("fechaCreacion.in=" + UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void getAllCAutobusesByFechaCreacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where fechaCreacion is not null
        defaultCAutobusShouldBeFound("fechaCreacion.specified=true");

        // Get all the cAutobusList where fechaCreacion is null
        defaultCAutobusShouldNotBeFound("fechaCreacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCAutobusesByIdUsuarioActualizacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where idUsuarioActualizacion equals to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCAutobusShouldBeFound("idUsuarioActualizacion.equals=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cAutobusList where idUsuarioActualizacion equals to UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCAutobusShouldNotBeFound("idUsuarioActualizacion.equals=" + UPDATED_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCAutobusesByIdUsuarioActualizacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where idUsuarioActualizacion not equals to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCAutobusShouldNotBeFound("idUsuarioActualizacion.notEquals=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cAutobusList where idUsuarioActualizacion not equals to UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCAutobusShouldBeFound("idUsuarioActualizacion.notEquals=" + UPDATED_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCAutobusesByIdUsuarioActualizacionIsInShouldWork() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where idUsuarioActualizacion in DEFAULT_ID_USUARIO_ACTUALIZACION or UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCAutobusShouldBeFound("idUsuarioActualizacion.in=" + DEFAULT_ID_USUARIO_ACTUALIZACION + "," + UPDATED_ID_USUARIO_ACTUALIZACION);

        // Get all the cAutobusList where idUsuarioActualizacion equals to UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCAutobusShouldNotBeFound("idUsuarioActualizacion.in=" + UPDATED_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCAutobusesByIdUsuarioActualizacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where idUsuarioActualizacion is not null
        defaultCAutobusShouldBeFound("idUsuarioActualizacion.specified=true");

        // Get all the cAutobusList where idUsuarioActualizacion is null
        defaultCAutobusShouldNotBeFound("idUsuarioActualizacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCAutobusesByIdUsuarioActualizacionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where idUsuarioActualizacion is greater than or equal to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCAutobusShouldBeFound("idUsuarioActualizacion.greaterThanOrEqual=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cAutobusList where idUsuarioActualizacion is greater than or equal to (DEFAULT_ID_USUARIO_ACTUALIZACION + 1)
        defaultCAutobusShouldNotBeFound("idUsuarioActualizacion.greaterThanOrEqual=" + (DEFAULT_ID_USUARIO_ACTUALIZACION + 1));
    }

    @Test
    @Transactional
    public void getAllCAutobusesByIdUsuarioActualizacionIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where idUsuarioActualizacion is less than or equal to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCAutobusShouldBeFound("idUsuarioActualizacion.lessThanOrEqual=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cAutobusList where idUsuarioActualizacion is less than or equal to SMALLER_ID_USUARIO_ACTUALIZACION
        defaultCAutobusShouldNotBeFound("idUsuarioActualizacion.lessThanOrEqual=" + SMALLER_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCAutobusesByIdUsuarioActualizacionIsLessThanSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where idUsuarioActualizacion is less than DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCAutobusShouldNotBeFound("idUsuarioActualizacion.lessThan=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cAutobusList where idUsuarioActualizacion is less than (DEFAULT_ID_USUARIO_ACTUALIZACION + 1)
        defaultCAutobusShouldBeFound("idUsuarioActualizacion.lessThan=" + (DEFAULT_ID_USUARIO_ACTUALIZACION + 1));
    }

    @Test
    @Transactional
    public void getAllCAutobusesByIdUsuarioActualizacionIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where idUsuarioActualizacion is greater than DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCAutobusShouldNotBeFound("idUsuarioActualizacion.greaterThan=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cAutobusList where idUsuarioActualizacion is greater than SMALLER_ID_USUARIO_ACTUALIZACION
        defaultCAutobusShouldBeFound("idUsuarioActualizacion.greaterThan=" + SMALLER_ID_USUARIO_ACTUALIZACION);
    }


    @Test
    @Transactional
    public void getAllCAutobusesByFechaActualizacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where fechaActualizacion equals to DEFAULT_FECHA_ACTUALIZACION
        defaultCAutobusShouldBeFound("fechaActualizacion.equals=" + DEFAULT_FECHA_ACTUALIZACION);

        // Get all the cAutobusList where fechaActualizacion equals to UPDATED_FECHA_ACTUALIZACION
        defaultCAutobusShouldNotBeFound("fechaActualizacion.equals=" + UPDATED_FECHA_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCAutobusesByFechaActualizacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where fechaActualizacion not equals to DEFAULT_FECHA_ACTUALIZACION
        defaultCAutobusShouldNotBeFound("fechaActualizacion.notEquals=" + DEFAULT_FECHA_ACTUALIZACION);

        // Get all the cAutobusList where fechaActualizacion not equals to UPDATED_FECHA_ACTUALIZACION
        defaultCAutobusShouldBeFound("fechaActualizacion.notEquals=" + UPDATED_FECHA_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCAutobusesByFechaActualizacionIsInShouldWork() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where fechaActualizacion in DEFAULT_FECHA_ACTUALIZACION or UPDATED_FECHA_ACTUALIZACION
        defaultCAutobusShouldBeFound("fechaActualizacion.in=" + DEFAULT_FECHA_ACTUALIZACION + "," + UPDATED_FECHA_ACTUALIZACION);

        // Get all the cAutobusList where fechaActualizacion equals to UPDATED_FECHA_ACTUALIZACION
        defaultCAutobusShouldNotBeFound("fechaActualizacion.in=" + UPDATED_FECHA_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCAutobusesByFechaActualizacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where fechaActualizacion is not null
        defaultCAutobusShouldBeFound("fechaActualizacion.specified=true");

        // Get all the cAutobusList where fechaActualizacion is null
        defaultCAutobusShouldNotBeFound("fechaActualizacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCAutobusesByNotasIsEqualToSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where notas equals to DEFAULT_NOTAS
        defaultCAutobusShouldBeFound("notas.equals=" + DEFAULT_NOTAS);

        // Get all the cAutobusList where notas equals to UPDATED_NOTAS
        defaultCAutobusShouldNotBeFound("notas.equals=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCAutobusesByNotasIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where notas not equals to DEFAULT_NOTAS
        defaultCAutobusShouldNotBeFound("notas.notEquals=" + DEFAULT_NOTAS);

        // Get all the cAutobusList where notas not equals to UPDATED_NOTAS
        defaultCAutobusShouldBeFound("notas.notEquals=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCAutobusesByNotasIsInShouldWork() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where notas in DEFAULT_NOTAS or UPDATED_NOTAS
        defaultCAutobusShouldBeFound("notas.in=" + DEFAULT_NOTAS + "," + UPDATED_NOTAS);

        // Get all the cAutobusList where notas equals to UPDATED_NOTAS
        defaultCAutobusShouldNotBeFound("notas.in=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCAutobusesByNotasIsNullOrNotNull() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where notas is not null
        defaultCAutobusShouldBeFound("notas.specified=true");

        // Get all the cAutobusList where notas is null
        defaultCAutobusShouldNotBeFound("notas.specified=false");
    }
                @Test
    @Transactional
    public void getAllCAutobusesByNotasContainsSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where notas contains DEFAULT_NOTAS
        defaultCAutobusShouldBeFound("notas.contains=" + DEFAULT_NOTAS);

        // Get all the cAutobusList where notas contains UPDATED_NOTAS
        defaultCAutobusShouldNotBeFound("notas.contains=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCAutobusesByNotasNotContainsSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where notas does not contain DEFAULT_NOTAS
        defaultCAutobusShouldNotBeFound("notas.doesNotContain=" + DEFAULT_NOTAS);

        // Get all the cAutobusList where notas does not contain UPDATED_NOTAS
        defaultCAutobusShouldBeFound("notas.doesNotContain=" + UPDATED_NOTAS);
    }


    @Test
    @Transactional
    public void getAllCAutobusesByEstatusIsEqualToSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where estatus equals to DEFAULT_ESTATUS
        defaultCAutobusShouldBeFound("estatus.equals=" + DEFAULT_ESTATUS);

        // Get all the cAutobusList where estatus equals to UPDATED_ESTATUS
        defaultCAutobusShouldNotBeFound("estatus.equals=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCAutobusesByEstatusIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where estatus not equals to DEFAULT_ESTATUS
        defaultCAutobusShouldNotBeFound("estatus.notEquals=" + DEFAULT_ESTATUS);

        // Get all the cAutobusList where estatus not equals to UPDATED_ESTATUS
        defaultCAutobusShouldBeFound("estatus.notEquals=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCAutobusesByEstatusIsInShouldWork() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where estatus in DEFAULT_ESTATUS or UPDATED_ESTATUS
        defaultCAutobusShouldBeFound("estatus.in=" + DEFAULT_ESTATUS + "," + UPDATED_ESTATUS);

        // Get all the cAutobusList where estatus equals to UPDATED_ESTATUS
        defaultCAutobusShouldNotBeFound("estatus.in=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCAutobusesByEstatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where estatus is not null
        defaultCAutobusShouldBeFound("estatus.specified=true");

        // Get all the cAutobusList where estatus is null
        defaultCAutobusShouldNotBeFound("estatus.specified=false");
    }

    @Test
    @Transactional
    public void getAllCAutobusesByEstatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where estatus is greater than or equal to DEFAULT_ESTATUS
        defaultCAutobusShouldBeFound("estatus.greaterThanOrEqual=" + DEFAULT_ESTATUS);

        // Get all the cAutobusList where estatus is greater than or equal to (DEFAULT_ESTATUS + 1)
        defaultCAutobusShouldNotBeFound("estatus.greaterThanOrEqual=" + (DEFAULT_ESTATUS + 1));
    }

    @Test
    @Transactional
    public void getAllCAutobusesByEstatusIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where estatus is less than or equal to DEFAULT_ESTATUS
        defaultCAutobusShouldBeFound("estatus.lessThanOrEqual=" + DEFAULT_ESTATUS);

        // Get all the cAutobusList where estatus is less than or equal to SMALLER_ESTATUS
        defaultCAutobusShouldNotBeFound("estatus.lessThanOrEqual=" + SMALLER_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCAutobusesByEstatusIsLessThanSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where estatus is less than DEFAULT_ESTATUS
        defaultCAutobusShouldNotBeFound("estatus.lessThan=" + DEFAULT_ESTATUS);

        // Get all the cAutobusList where estatus is less than (DEFAULT_ESTATUS + 1)
        defaultCAutobusShouldBeFound("estatus.lessThan=" + (DEFAULT_ESTATUS + 1));
    }

    @Test
    @Transactional
    public void getAllCAutobusesByEstatusIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where estatus is greater than DEFAULT_ESTATUS
        defaultCAutobusShouldNotBeFound("estatus.greaterThan=" + DEFAULT_ESTATUS);

        // Get all the cAutobusList where estatus is greater than SMALLER_ESTATUS
        defaultCAutobusShouldBeFound("estatus.greaterThan=" + SMALLER_ESTATUS);
    }


    @Test
    @Transactional
    public void getAllCAutobusesByBorradoIsEqualToSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where borrado equals to DEFAULT_BORRADO
        defaultCAutobusShouldBeFound("borrado.equals=" + DEFAULT_BORRADO);

        // Get all the cAutobusList where borrado equals to UPDATED_BORRADO
        defaultCAutobusShouldNotBeFound("borrado.equals=" + UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCAutobusesByBorradoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where borrado not equals to DEFAULT_BORRADO
        defaultCAutobusShouldNotBeFound("borrado.notEquals=" + DEFAULT_BORRADO);

        // Get all the cAutobusList where borrado not equals to UPDATED_BORRADO
        defaultCAutobusShouldBeFound("borrado.notEquals=" + UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCAutobusesByBorradoIsInShouldWork() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where borrado in DEFAULT_BORRADO or UPDATED_BORRADO
        defaultCAutobusShouldBeFound("borrado.in=" + DEFAULT_BORRADO + "," + UPDATED_BORRADO);

        // Get all the cAutobusList where borrado equals to UPDATED_BORRADO
        defaultCAutobusShouldNotBeFound("borrado.in=" + UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCAutobusesByBorradoIsNullOrNotNull() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where borrado is not null
        defaultCAutobusShouldBeFound("borrado.specified=true");

        // Get all the cAutobusList where borrado is null
        defaultCAutobusShouldNotBeFound("borrado.specified=false");
    }

    @Test
    @Transactional
    public void getAllCAutobusesByBorradoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where borrado is greater than or equal to DEFAULT_BORRADO
        defaultCAutobusShouldBeFound("borrado.greaterThanOrEqual=" + DEFAULT_BORRADO);

        // Get all the cAutobusList where borrado is greater than or equal to (DEFAULT_BORRADO + 1)
        defaultCAutobusShouldNotBeFound("borrado.greaterThanOrEqual=" + (DEFAULT_BORRADO + 1));
    }

    @Test
    @Transactional
    public void getAllCAutobusesByBorradoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where borrado is less than or equal to DEFAULT_BORRADO
        defaultCAutobusShouldBeFound("borrado.lessThanOrEqual=" + DEFAULT_BORRADO);

        // Get all the cAutobusList where borrado is less than or equal to SMALLER_BORRADO
        defaultCAutobusShouldNotBeFound("borrado.lessThanOrEqual=" + SMALLER_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCAutobusesByBorradoIsLessThanSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where borrado is less than DEFAULT_BORRADO
        defaultCAutobusShouldNotBeFound("borrado.lessThan=" + DEFAULT_BORRADO);

        // Get all the cAutobusList where borrado is less than (DEFAULT_BORRADO + 1)
        defaultCAutobusShouldBeFound("borrado.lessThan=" + (DEFAULT_BORRADO + 1));
    }

    @Test
    @Transactional
    public void getAllCAutobusesByBorradoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        // Get all the cAutobusList where borrado is greater than DEFAULT_BORRADO
        defaultCAutobusShouldNotBeFound("borrado.greaterThan=" + DEFAULT_BORRADO);

        // Get all the cAutobusList where borrado is greater than SMALLER_BORRADO
        defaultCAutobusShouldBeFound("borrado.greaterThan=" + SMALLER_BORRADO);
    }


    @Test
    @Transactional
    public void getAllCAutobusesByClienteIsEqualToSomething() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);
        CCliente cliente = CClienteResourceIT.createEntity(em);
        em.persist(cliente);
        em.flush();
        cAutobus.setCliente(cliente);
        cAutobusRepository.saveAndFlush(cAutobus);
        Long clienteId = cliente.getId();

        // Get all the cAutobusList where cliente equals to clienteId
        defaultCAutobusShouldBeFound("clienteId.equals=" + clienteId);

        // Get all the cAutobusList where cliente equals to clienteId + 1
        defaultCAutobusShouldNotBeFound("clienteId.equals=" + (clienteId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCAutobusShouldBeFound(String filter) throws Exception {
        restCAutobusMockMvc.perform(get("/api/c-autobuses?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cAutobus.getId().intValue())))
            .andExpect(jsonPath("$.[*].autobus").value(hasItem(DEFAULT_AUTOBUS)))
            .andExpect(jsonPath("$.[*].idUsuarioCreacion").value(hasItem(DEFAULT_ID_USUARIO_CREACION.intValue())))
            .andExpect(jsonPath("$.[*].fechaCreacion").value(hasItem(DEFAULT_FECHA_CREACION.toString())))
            .andExpect(jsonPath("$.[*].idUsuarioActualizacion").value(hasItem(DEFAULT_ID_USUARIO_ACTUALIZACION.intValue())))
            .andExpect(jsonPath("$.[*].fechaActualizacion").value(hasItem(DEFAULT_FECHA_ACTUALIZACION.toString())))
            .andExpect(jsonPath("$.[*].notas").value(hasItem(DEFAULT_NOTAS)))
            .andExpect(jsonPath("$.[*].estatus").value(hasItem(DEFAULT_ESTATUS)))
            .andExpect(jsonPath("$.[*].borrado").value(hasItem(DEFAULT_BORRADO)));

        // Check, that the count call also returns 1
        restCAutobusMockMvc.perform(get("/api/c-autobuses/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCAutobusShouldNotBeFound(String filter) throws Exception {
        restCAutobusMockMvc.perform(get("/api/c-autobuses?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCAutobusMockMvc.perform(get("/api/c-autobuses/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCAutobus() throws Exception {
        // Get the cAutobus
        restCAutobusMockMvc.perform(get("/api/c-autobuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCAutobus() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        int databaseSizeBeforeUpdate = cAutobusRepository.findAll().size();

        // Update the cAutobus
        CAutobus updatedCAutobus = cAutobusRepository.findById(cAutobus.getId()).get();
        // Disconnect from session so that the updates on updatedCAutobus are not directly saved in db
        em.detach(updatedCAutobus);
        updatedCAutobus
            .autobus(UPDATED_AUTOBUS)
            .idUsuarioCreacion(UPDATED_ID_USUARIO_CREACION)
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .idUsuarioActualizacion(UPDATED_ID_USUARIO_ACTUALIZACION)
            .fechaActualizacion(UPDATED_FECHA_ACTUALIZACION)
            .notas(UPDATED_NOTAS)
            .estatus(UPDATED_ESTATUS)
            .borrado(UPDATED_BORRADO);
        CAutobusDTO cAutobusDTO = cAutobusMapper.toDto(updatedCAutobus);

        restCAutobusMockMvc.perform(put("/api/c-autobuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cAutobusDTO)))
            .andExpect(status().isOk());

        // Validate the CAutobus in the database
        List<CAutobus> cAutobusList = cAutobusRepository.findAll();
        assertThat(cAutobusList).hasSize(databaseSizeBeforeUpdate);
        CAutobus testCAutobus = cAutobusList.get(cAutobusList.size() - 1);
        assertThat(testCAutobus.getAutobus()).isEqualTo(UPDATED_AUTOBUS);
        assertThat(testCAutobus.getIdUsuarioCreacion()).isEqualTo(UPDATED_ID_USUARIO_CREACION);
        assertThat(testCAutobus.getFechaCreacion()).isEqualTo(UPDATED_FECHA_CREACION);
        assertThat(testCAutobus.getIdUsuarioActualizacion()).isEqualTo(UPDATED_ID_USUARIO_ACTUALIZACION);
        assertThat(testCAutobus.getFechaActualizacion()).isEqualTo(UPDATED_FECHA_ACTUALIZACION);
        assertThat(testCAutobus.getNotas()).isEqualTo(UPDATED_NOTAS);
        assertThat(testCAutobus.getEstatus()).isEqualTo(UPDATED_ESTATUS);
        assertThat(testCAutobus.getBorrado()).isEqualTo(UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void updateNonExistingCAutobus() throws Exception {
        int databaseSizeBeforeUpdate = cAutobusRepository.findAll().size();

        // Create the CAutobus
        CAutobusDTO cAutobusDTO = cAutobusMapper.toDto(cAutobus);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCAutobusMockMvc.perform(put("/api/c-autobuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cAutobusDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CAutobus in the database
        List<CAutobus> cAutobusList = cAutobusRepository.findAll();
        assertThat(cAutobusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCAutobus() throws Exception {
        // Initialize the database
        cAutobusRepository.saveAndFlush(cAutobus);

        int databaseSizeBeforeDelete = cAutobusRepository.findAll().size();

        // Delete the cAutobus
        restCAutobusMockMvc.perform(delete("/api/c-autobuses/{id}", cAutobus.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CAutobus> cAutobusList = cAutobusRepository.findAll();
        assertThat(cAutobusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
