package mx.com.tordavi.web.rest;

import mx.com.tordavi.TordaviApp;
import mx.com.tordavi.domain.CPasajero;
import mx.com.tordavi.domain.CCliente;
import mx.com.tordavi.repository.CPasajeroRepository;
import mx.com.tordavi.service.CPasajeroService;
import mx.com.tordavi.service.dto.CPasajeroDTO;
import mx.com.tordavi.service.mapper.CPasajeroMapper;
import mx.com.tordavi.service.dto.CPasajeroCriteria;
import mx.com.tordavi.service.CPasajeroQueryService;

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
 * Integration tests for the {@link CPasajeroResource} REST controller.
 */
@SpringBootTest(classes = TordaviApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class CPasajeroResourceIT {

    private static final String DEFAULT_NOMBRE_COMPLETO = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_COMPLETO = "BBBBBBBBBB";

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_SEGUNDO_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_SEGUNDO_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_APELLIDO = "AAAAAAAAAA";
    private static final String UPDATED_APELLIDO = "BBBBBBBBBB";

    private static final String DEFAULT_SEGUNDO_APELLIDO = "AAAAAAAAAA";
    private static final String UPDATED_SEGUNDO_APELLIDO = "BBBBBBBBBB";

    private static final Integer DEFAULT_EDAD = 999;
    private static final Integer UPDATED_EDAD = 998;
    private static final Integer SMALLER_EDAD = 999 - 1;

    private static final String DEFAULT_CURP = "AAAAAAAAAA";
    private static final String UPDATED_CURP = "BBBBBBBBBB";

    private static final String DEFAULT_CIUDADANIA = "AAAAAAAAAA";
    private static final String UPDATED_CIUDADANIA = "BBBBBBBBBB";

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
    private CPasajeroRepository cPasajeroRepository;

    @Autowired
    private CPasajeroMapper cPasajeroMapper;

    @Autowired
    private CPasajeroService cPasajeroService;

    @Autowired
    private CPasajeroQueryService cPasajeroQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCPasajeroMockMvc;

    private CPasajero cPasajero;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CPasajero createEntity(EntityManager em) {
        CPasajero cPasajero = new CPasajero()
            .nombreCompleto(DEFAULT_NOMBRE_COMPLETO)
            .nombre(DEFAULT_NOMBRE)
            .segundoNombre(DEFAULT_SEGUNDO_NOMBRE)
            .apellido(DEFAULT_APELLIDO)
            .segundoApellido(DEFAULT_SEGUNDO_APELLIDO)
            .edad(DEFAULT_EDAD)
            .curp(DEFAULT_CURP)
            .ciudadania(DEFAULT_CIUDADANIA)
            .idUsuarioCreacion(DEFAULT_ID_USUARIO_CREACION)
            .fechaCreacion(DEFAULT_FECHA_CREACION)
            .idUsuarioActualizacion(DEFAULT_ID_USUARIO_ACTUALIZACION)
            .fechaActualizacion(DEFAULT_FECHA_ACTUALIZACION)
            .notas(DEFAULT_NOTAS)
            .estatus(DEFAULT_ESTATUS)
            .borrado(DEFAULT_BORRADO);
        return cPasajero;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CPasajero createUpdatedEntity(EntityManager em) {
        CPasajero cPasajero = new CPasajero()
            .nombreCompleto(UPDATED_NOMBRE_COMPLETO)
            .nombre(UPDATED_NOMBRE)
            .segundoNombre(UPDATED_SEGUNDO_NOMBRE)
            .apellido(UPDATED_APELLIDO)
            .segundoApellido(UPDATED_SEGUNDO_APELLIDO)
            .edad(UPDATED_EDAD)
            .curp(UPDATED_CURP)
            .ciudadania(UPDATED_CIUDADANIA)
            .idUsuarioCreacion(UPDATED_ID_USUARIO_CREACION)
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .idUsuarioActualizacion(UPDATED_ID_USUARIO_ACTUALIZACION)
            .fechaActualizacion(UPDATED_FECHA_ACTUALIZACION)
            .notas(UPDATED_NOTAS)
            .estatus(UPDATED_ESTATUS)
            .borrado(UPDATED_BORRADO);
        return cPasajero;
    }

    @BeforeEach
    public void initTest() {
        cPasajero = createEntity(em);
    }

    @Test
    @Transactional
    public void createCPasajero() throws Exception {
        int databaseSizeBeforeCreate = cPasajeroRepository.findAll().size();

        // Create the CPasajero
        CPasajeroDTO cPasajeroDTO = cPasajeroMapper.toDto(cPasajero);
        restCPasajeroMockMvc.perform(post("/api/c-pasajeros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cPasajeroDTO)))
            .andExpect(status().isCreated());

        // Validate the CPasajero in the database
        List<CPasajero> cPasajeroList = cPasajeroRepository.findAll();
        assertThat(cPasajeroList).hasSize(databaseSizeBeforeCreate + 1);
        CPasajero testCPasajero = cPasajeroList.get(cPasajeroList.size() - 1);
        assertThat(testCPasajero.getNombreCompleto()).isEqualTo(DEFAULT_NOMBRE_COMPLETO);
        assertThat(testCPasajero.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testCPasajero.getSegundoNombre()).isEqualTo(DEFAULT_SEGUNDO_NOMBRE);
        assertThat(testCPasajero.getApellido()).isEqualTo(DEFAULT_APELLIDO);
        assertThat(testCPasajero.getSegundoApellido()).isEqualTo(DEFAULT_SEGUNDO_APELLIDO);
        assertThat(testCPasajero.getEdad()).isEqualTo(DEFAULT_EDAD);
        assertThat(testCPasajero.getCurp()).isEqualTo(DEFAULT_CURP);
        assertThat(testCPasajero.getCiudadania()).isEqualTo(DEFAULT_CIUDADANIA);
        assertThat(testCPasajero.getIdUsuarioCreacion()).isEqualTo(DEFAULT_ID_USUARIO_CREACION);
        assertThat(testCPasajero.getFechaCreacion()).isEqualTo(DEFAULT_FECHA_CREACION);
        assertThat(testCPasajero.getIdUsuarioActualizacion()).isEqualTo(DEFAULT_ID_USUARIO_ACTUALIZACION);
        assertThat(testCPasajero.getFechaActualizacion()).isEqualTo(DEFAULT_FECHA_ACTUALIZACION);
        assertThat(testCPasajero.getNotas()).isEqualTo(DEFAULT_NOTAS);
        assertThat(testCPasajero.getEstatus()).isEqualTo(DEFAULT_ESTATUS);
        assertThat(testCPasajero.getBorrado()).isEqualTo(DEFAULT_BORRADO);
    }

    @Test
    @Transactional
    public void createCPasajeroWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cPasajeroRepository.findAll().size();

        // Create the CPasajero with an existing ID
        cPasajero.setId(1L);
        CPasajeroDTO cPasajeroDTO = cPasajeroMapper.toDto(cPasajero);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCPasajeroMockMvc.perform(post("/api/c-pasajeros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cPasajeroDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CPasajero in the database
        List<CPasajero> cPasajeroList = cPasajeroRepository.findAll();
        assertThat(cPasajeroList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNombreCompletoIsRequired() throws Exception {
        int databaseSizeBeforeTest = cPasajeroRepository.findAll().size();
        // set the field null
        cPasajero.setNombreCompleto(null);

        // Create the CPasajero, which fails.
        CPasajeroDTO cPasajeroDTO = cPasajeroMapper.toDto(cPasajero);

        restCPasajeroMockMvc.perform(post("/api/c-pasajeros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cPasajeroDTO)))
            .andExpect(status().isBadRequest());

        List<CPasajero> cPasajeroList = cPasajeroRepository.findAll();
        assertThat(cPasajeroList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = cPasajeroRepository.findAll().size();
        // set the field null
        cPasajero.setNombre(null);

        // Create the CPasajero, which fails.
        CPasajeroDTO cPasajeroDTO = cPasajeroMapper.toDto(cPasajero);

        restCPasajeroMockMvc.perform(post("/api/c-pasajeros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cPasajeroDTO)))
            .andExpect(status().isBadRequest());

        List<CPasajero> cPasajeroList = cPasajeroRepository.findAll();
        assertThat(cPasajeroList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkApellidoIsRequired() throws Exception {
        int databaseSizeBeforeTest = cPasajeroRepository.findAll().size();
        // set the field null
        cPasajero.setApellido(null);

        // Create the CPasajero, which fails.
        CPasajeroDTO cPasajeroDTO = cPasajeroMapper.toDto(cPasajero);

        restCPasajeroMockMvc.perform(post("/api/c-pasajeros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cPasajeroDTO)))
            .andExpect(status().isBadRequest());

        List<CPasajero> cPasajeroList = cPasajeroRepository.findAll();
        assertThat(cPasajeroList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdUsuarioCreacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = cPasajeroRepository.findAll().size();
        // set the field null
        cPasajero.setIdUsuarioCreacion(null);

        // Create the CPasajero, which fails.
        CPasajeroDTO cPasajeroDTO = cPasajeroMapper.toDto(cPasajero);

        restCPasajeroMockMvc.perform(post("/api/c-pasajeros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cPasajeroDTO)))
            .andExpect(status().isBadRequest());

        List<CPasajero> cPasajeroList = cPasajeroRepository.findAll();
        assertThat(cPasajeroList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFechaCreacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = cPasajeroRepository.findAll().size();
        // set the field null
        cPasajero.setFechaCreacion(null);

        // Create the CPasajero, which fails.
        CPasajeroDTO cPasajeroDTO = cPasajeroMapper.toDto(cPasajero);

        restCPasajeroMockMvc.perform(post("/api/c-pasajeros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cPasajeroDTO)))
            .andExpect(status().isBadRequest());

        List<CPasajero> cPasajeroList = cPasajeroRepository.findAll();
        assertThat(cPasajeroList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = cPasajeroRepository.findAll().size();
        // set the field null
        cPasajero.setEstatus(null);

        // Create the CPasajero, which fails.
        CPasajeroDTO cPasajeroDTO = cPasajeroMapper.toDto(cPasajero);

        restCPasajeroMockMvc.perform(post("/api/c-pasajeros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cPasajeroDTO)))
            .andExpect(status().isBadRequest());

        List<CPasajero> cPasajeroList = cPasajeroRepository.findAll();
        assertThat(cPasajeroList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBorradoIsRequired() throws Exception {
        int databaseSizeBeforeTest = cPasajeroRepository.findAll().size();
        // set the field null
        cPasajero.setBorrado(null);

        // Create the CPasajero, which fails.
        CPasajeroDTO cPasajeroDTO = cPasajeroMapper.toDto(cPasajero);

        restCPasajeroMockMvc.perform(post("/api/c-pasajeros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cPasajeroDTO)))
            .andExpect(status().isBadRequest());

        List<CPasajero> cPasajeroList = cPasajeroRepository.findAll();
        assertThat(cPasajeroList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCPasajeros() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList
        restCPasajeroMockMvc.perform(get("/api/c-pasajeros?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cPasajero.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombreCompleto").value(hasItem(DEFAULT_NOMBRE_COMPLETO)))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].segundoNombre").value(hasItem(DEFAULT_SEGUNDO_NOMBRE)))
            .andExpect(jsonPath("$.[*].apellido").value(hasItem(DEFAULT_APELLIDO)))
            .andExpect(jsonPath("$.[*].segundoApellido").value(hasItem(DEFAULT_SEGUNDO_APELLIDO)))
            .andExpect(jsonPath("$.[*].edad").value(hasItem(DEFAULT_EDAD)))
            .andExpect(jsonPath("$.[*].curp").value(hasItem(DEFAULT_CURP)))
            .andExpect(jsonPath("$.[*].ciudadania").value(hasItem(DEFAULT_CIUDADANIA)))
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
    public void getCPasajero() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get the cPasajero
        restCPasajeroMockMvc.perform(get("/api/c-pasajeros/{id}", cPasajero.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cPasajero.getId().intValue()))
            .andExpect(jsonPath("$.nombreCompleto").value(DEFAULT_NOMBRE_COMPLETO))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.segundoNombre").value(DEFAULT_SEGUNDO_NOMBRE))
            .andExpect(jsonPath("$.apellido").value(DEFAULT_APELLIDO))
            .andExpect(jsonPath("$.segundoApellido").value(DEFAULT_SEGUNDO_APELLIDO))
            .andExpect(jsonPath("$.edad").value(DEFAULT_EDAD))
            .andExpect(jsonPath("$.curp").value(DEFAULT_CURP))
            .andExpect(jsonPath("$.ciudadania").value(DEFAULT_CIUDADANIA))
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
    public void getCPasajerosByIdFiltering() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        Long id = cPasajero.getId();

        defaultCPasajeroShouldBeFound("id.equals=" + id);
        defaultCPasajeroShouldNotBeFound("id.notEquals=" + id);

        defaultCPasajeroShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCPasajeroShouldNotBeFound("id.greaterThan=" + id);

        defaultCPasajeroShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCPasajeroShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllCPasajerosByNombreCompletoIsEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where nombreCompleto equals to DEFAULT_NOMBRE_COMPLETO
        defaultCPasajeroShouldBeFound("nombreCompleto.equals=" + DEFAULT_NOMBRE_COMPLETO);

        // Get all the cPasajeroList where nombreCompleto equals to UPDATED_NOMBRE_COMPLETO
        defaultCPasajeroShouldNotBeFound("nombreCompleto.equals=" + UPDATED_NOMBRE_COMPLETO);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByNombreCompletoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where nombreCompleto not equals to DEFAULT_NOMBRE_COMPLETO
        defaultCPasajeroShouldNotBeFound("nombreCompleto.notEquals=" + DEFAULT_NOMBRE_COMPLETO);

        // Get all the cPasajeroList where nombreCompleto not equals to UPDATED_NOMBRE_COMPLETO
        defaultCPasajeroShouldBeFound("nombreCompleto.notEquals=" + UPDATED_NOMBRE_COMPLETO);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByNombreCompletoIsInShouldWork() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where nombreCompleto in DEFAULT_NOMBRE_COMPLETO or UPDATED_NOMBRE_COMPLETO
        defaultCPasajeroShouldBeFound("nombreCompleto.in=" + DEFAULT_NOMBRE_COMPLETO + "," + UPDATED_NOMBRE_COMPLETO);

        // Get all the cPasajeroList where nombreCompleto equals to UPDATED_NOMBRE_COMPLETO
        defaultCPasajeroShouldNotBeFound("nombreCompleto.in=" + UPDATED_NOMBRE_COMPLETO);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByNombreCompletoIsNullOrNotNull() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where nombreCompleto is not null
        defaultCPasajeroShouldBeFound("nombreCompleto.specified=true");

        // Get all the cPasajeroList where nombreCompleto is null
        defaultCPasajeroShouldNotBeFound("nombreCompleto.specified=false");
    }
                @Test
    @Transactional
    public void getAllCPasajerosByNombreCompletoContainsSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where nombreCompleto contains DEFAULT_NOMBRE_COMPLETO
        defaultCPasajeroShouldBeFound("nombreCompleto.contains=" + DEFAULT_NOMBRE_COMPLETO);

        // Get all the cPasajeroList where nombreCompleto contains UPDATED_NOMBRE_COMPLETO
        defaultCPasajeroShouldNotBeFound("nombreCompleto.contains=" + UPDATED_NOMBRE_COMPLETO);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByNombreCompletoNotContainsSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where nombreCompleto does not contain DEFAULT_NOMBRE_COMPLETO
        defaultCPasajeroShouldNotBeFound("nombreCompleto.doesNotContain=" + DEFAULT_NOMBRE_COMPLETO);

        // Get all the cPasajeroList where nombreCompleto does not contain UPDATED_NOMBRE_COMPLETO
        defaultCPasajeroShouldBeFound("nombreCompleto.doesNotContain=" + UPDATED_NOMBRE_COMPLETO);
    }


    @Test
    @Transactional
    public void getAllCPasajerosByNombreIsEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where nombre equals to DEFAULT_NOMBRE
        defaultCPasajeroShouldBeFound("nombre.equals=" + DEFAULT_NOMBRE);

        // Get all the cPasajeroList where nombre equals to UPDATED_NOMBRE
        defaultCPasajeroShouldNotBeFound("nombre.equals=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByNombreIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where nombre not equals to DEFAULT_NOMBRE
        defaultCPasajeroShouldNotBeFound("nombre.notEquals=" + DEFAULT_NOMBRE);

        // Get all the cPasajeroList where nombre not equals to UPDATED_NOMBRE
        defaultCPasajeroShouldBeFound("nombre.notEquals=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByNombreIsInShouldWork() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where nombre in DEFAULT_NOMBRE or UPDATED_NOMBRE
        defaultCPasajeroShouldBeFound("nombre.in=" + DEFAULT_NOMBRE + "," + UPDATED_NOMBRE);

        // Get all the cPasajeroList where nombre equals to UPDATED_NOMBRE
        defaultCPasajeroShouldNotBeFound("nombre.in=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByNombreIsNullOrNotNull() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where nombre is not null
        defaultCPasajeroShouldBeFound("nombre.specified=true");

        // Get all the cPasajeroList where nombre is null
        defaultCPasajeroShouldNotBeFound("nombre.specified=false");
    }
                @Test
    @Transactional
    public void getAllCPasajerosByNombreContainsSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where nombre contains DEFAULT_NOMBRE
        defaultCPasajeroShouldBeFound("nombre.contains=" + DEFAULT_NOMBRE);

        // Get all the cPasajeroList where nombre contains UPDATED_NOMBRE
        defaultCPasajeroShouldNotBeFound("nombre.contains=" + UPDATED_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByNombreNotContainsSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where nombre does not contain DEFAULT_NOMBRE
        defaultCPasajeroShouldNotBeFound("nombre.doesNotContain=" + DEFAULT_NOMBRE);

        // Get all the cPasajeroList where nombre does not contain UPDATED_NOMBRE
        defaultCPasajeroShouldBeFound("nombre.doesNotContain=" + UPDATED_NOMBRE);
    }


    @Test
    @Transactional
    public void getAllCPasajerosBySegundoNombreIsEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where segundoNombre equals to DEFAULT_SEGUNDO_NOMBRE
        defaultCPasajeroShouldBeFound("segundoNombre.equals=" + DEFAULT_SEGUNDO_NOMBRE);

        // Get all the cPasajeroList where segundoNombre equals to UPDATED_SEGUNDO_NOMBRE
        defaultCPasajeroShouldNotBeFound("segundoNombre.equals=" + UPDATED_SEGUNDO_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllCPasajerosBySegundoNombreIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where segundoNombre not equals to DEFAULT_SEGUNDO_NOMBRE
        defaultCPasajeroShouldNotBeFound("segundoNombre.notEquals=" + DEFAULT_SEGUNDO_NOMBRE);

        // Get all the cPasajeroList where segundoNombre not equals to UPDATED_SEGUNDO_NOMBRE
        defaultCPasajeroShouldBeFound("segundoNombre.notEquals=" + UPDATED_SEGUNDO_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllCPasajerosBySegundoNombreIsInShouldWork() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where segundoNombre in DEFAULT_SEGUNDO_NOMBRE or UPDATED_SEGUNDO_NOMBRE
        defaultCPasajeroShouldBeFound("segundoNombre.in=" + DEFAULT_SEGUNDO_NOMBRE + "," + UPDATED_SEGUNDO_NOMBRE);

        // Get all the cPasajeroList where segundoNombre equals to UPDATED_SEGUNDO_NOMBRE
        defaultCPasajeroShouldNotBeFound("segundoNombre.in=" + UPDATED_SEGUNDO_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllCPasajerosBySegundoNombreIsNullOrNotNull() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where segundoNombre is not null
        defaultCPasajeroShouldBeFound("segundoNombre.specified=true");

        // Get all the cPasajeroList where segundoNombre is null
        defaultCPasajeroShouldNotBeFound("segundoNombre.specified=false");
    }
                @Test
    @Transactional
    public void getAllCPasajerosBySegundoNombreContainsSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where segundoNombre contains DEFAULT_SEGUNDO_NOMBRE
        defaultCPasajeroShouldBeFound("segundoNombre.contains=" + DEFAULT_SEGUNDO_NOMBRE);

        // Get all the cPasajeroList where segundoNombre contains UPDATED_SEGUNDO_NOMBRE
        defaultCPasajeroShouldNotBeFound("segundoNombre.contains=" + UPDATED_SEGUNDO_NOMBRE);
    }

    @Test
    @Transactional
    public void getAllCPasajerosBySegundoNombreNotContainsSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where segundoNombre does not contain DEFAULT_SEGUNDO_NOMBRE
        defaultCPasajeroShouldNotBeFound("segundoNombre.doesNotContain=" + DEFAULT_SEGUNDO_NOMBRE);

        // Get all the cPasajeroList where segundoNombre does not contain UPDATED_SEGUNDO_NOMBRE
        defaultCPasajeroShouldBeFound("segundoNombre.doesNotContain=" + UPDATED_SEGUNDO_NOMBRE);
    }


    @Test
    @Transactional
    public void getAllCPasajerosByApellidoIsEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where apellido equals to DEFAULT_APELLIDO
        defaultCPasajeroShouldBeFound("apellido.equals=" + DEFAULT_APELLIDO);

        // Get all the cPasajeroList where apellido equals to UPDATED_APELLIDO
        defaultCPasajeroShouldNotBeFound("apellido.equals=" + UPDATED_APELLIDO);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByApellidoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where apellido not equals to DEFAULT_APELLIDO
        defaultCPasajeroShouldNotBeFound("apellido.notEquals=" + DEFAULT_APELLIDO);

        // Get all the cPasajeroList where apellido not equals to UPDATED_APELLIDO
        defaultCPasajeroShouldBeFound("apellido.notEquals=" + UPDATED_APELLIDO);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByApellidoIsInShouldWork() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where apellido in DEFAULT_APELLIDO or UPDATED_APELLIDO
        defaultCPasajeroShouldBeFound("apellido.in=" + DEFAULT_APELLIDO + "," + UPDATED_APELLIDO);

        // Get all the cPasajeroList where apellido equals to UPDATED_APELLIDO
        defaultCPasajeroShouldNotBeFound("apellido.in=" + UPDATED_APELLIDO);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByApellidoIsNullOrNotNull() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where apellido is not null
        defaultCPasajeroShouldBeFound("apellido.specified=true");

        // Get all the cPasajeroList where apellido is null
        defaultCPasajeroShouldNotBeFound("apellido.specified=false");
    }
                @Test
    @Transactional
    public void getAllCPasajerosByApellidoContainsSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where apellido contains DEFAULT_APELLIDO
        defaultCPasajeroShouldBeFound("apellido.contains=" + DEFAULT_APELLIDO);

        // Get all the cPasajeroList where apellido contains UPDATED_APELLIDO
        defaultCPasajeroShouldNotBeFound("apellido.contains=" + UPDATED_APELLIDO);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByApellidoNotContainsSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where apellido does not contain DEFAULT_APELLIDO
        defaultCPasajeroShouldNotBeFound("apellido.doesNotContain=" + DEFAULT_APELLIDO);

        // Get all the cPasajeroList where apellido does not contain UPDATED_APELLIDO
        defaultCPasajeroShouldBeFound("apellido.doesNotContain=" + UPDATED_APELLIDO);
    }


    @Test
    @Transactional
    public void getAllCPasajerosBySegundoApellidoIsEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where segundoApellido equals to DEFAULT_SEGUNDO_APELLIDO
        defaultCPasajeroShouldBeFound("segundoApellido.equals=" + DEFAULT_SEGUNDO_APELLIDO);

        // Get all the cPasajeroList where segundoApellido equals to UPDATED_SEGUNDO_APELLIDO
        defaultCPasajeroShouldNotBeFound("segundoApellido.equals=" + UPDATED_SEGUNDO_APELLIDO);
    }

    @Test
    @Transactional
    public void getAllCPasajerosBySegundoApellidoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where segundoApellido not equals to DEFAULT_SEGUNDO_APELLIDO
        defaultCPasajeroShouldNotBeFound("segundoApellido.notEquals=" + DEFAULT_SEGUNDO_APELLIDO);

        // Get all the cPasajeroList where segundoApellido not equals to UPDATED_SEGUNDO_APELLIDO
        defaultCPasajeroShouldBeFound("segundoApellido.notEquals=" + UPDATED_SEGUNDO_APELLIDO);
    }

    @Test
    @Transactional
    public void getAllCPasajerosBySegundoApellidoIsInShouldWork() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where segundoApellido in DEFAULT_SEGUNDO_APELLIDO or UPDATED_SEGUNDO_APELLIDO
        defaultCPasajeroShouldBeFound("segundoApellido.in=" + DEFAULT_SEGUNDO_APELLIDO + "," + UPDATED_SEGUNDO_APELLIDO);

        // Get all the cPasajeroList where segundoApellido equals to UPDATED_SEGUNDO_APELLIDO
        defaultCPasajeroShouldNotBeFound("segundoApellido.in=" + UPDATED_SEGUNDO_APELLIDO);
    }

    @Test
    @Transactional
    public void getAllCPasajerosBySegundoApellidoIsNullOrNotNull() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where segundoApellido is not null
        defaultCPasajeroShouldBeFound("segundoApellido.specified=true");

        // Get all the cPasajeroList where segundoApellido is null
        defaultCPasajeroShouldNotBeFound("segundoApellido.specified=false");
    }
                @Test
    @Transactional
    public void getAllCPasajerosBySegundoApellidoContainsSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where segundoApellido contains DEFAULT_SEGUNDO_APELLIDO
        defaultCPasajeroShouldBeFound("segundoApellido.contains=" + DEFAULT_SEGUNDO_APELLIDO);

        // Get all the cPasajeroList where segundoApellido contains UPDATED_SEGUNDO_APELLIDO
        defaultCPasajeroShouldNotBeFound("segundoApellido.contains=" + UPDATED_SEGUNDO_APELLIDO);
    }

    @Test
    @Transactional
    public void getAllCPasajerosBySegundoApellidoNotContainsSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where segundoApellido does not contain DEFAULT_SEGUNDO_APELLIDO
        defaultCPasajeroShouldNotBeFound("segundoApellido.doesNotContain=" + DEFAULT_SEGUNDO_APELLIDO);

        // Get all the cPasajeroList where segundoApellido does not contain UPDATED_SEGUNDO_APELLIDO
        defaultCPasajeroShouldBeFound("segundoApellido.doesNotContain=" + UPDATED_SEGUNDO_APELLIDO);
    }


    @Test
    @Transactional
    public void getAllCPasajerosByEdadIsEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where edad equals to DEFAULT_EDAD
        defaultCPasajeroShouldBeFound("edad.equals=" + DEFAULT_EDAD);

        // Get all the cPasajeroList where edad equals to UPDATED_EDAD
        defaultCPasajeroShouldNotBeFound("edad.equals=" + UPDATED_EDAD);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByEdadIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where edad not equals to DEFAULT_EDAD
        defaultCPasajeroShouldNotBeFound("edad.notEquals=" + DEFAULT_EDAD);

        // Get all the cPasajeroList where edad not equals to UPDATED_EDAD
        defaultCPasajeroShouldBeFound("edad.notEquals=" + UPDATED_EDAD);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByEdadIsInShouldWork() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where edad in DEFAULT_EDAD or UPDATED_EDAD
        defaultCPasajeroShouldBeFound("edad.in=" + DEFAULT_EDAD + "," + UPDATED_EDAD);

        // Get all the cPasajeroList where edad equals to UPDATED_EDAD
        defaultCPasajeroShouldNotBeFound("edad.in=" + UPDATED_EDAD);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByEdadIsNullOrNotNull() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where edad is not null
        defaultCPasajeroShouldBeFound("edad.specified=true");

        // Get all the cPasajeroList where edad is null
        defaultCPasajeroShouldNotBeFound("edad.specified=false");
    }

    @Test
    @Transactional
    public void getAllCPasajerosByEdadIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where edad is greater than or equal to DEFAULT_EDAD
        defaultCPasajeroShouldBeFound("edad.greaterThanOrEqual=" + DEFAULT_EDAD);

        // Get all the cPasajeroList where edad is greater than or equal to (DEFAULT_EDAD + 1)
        defaultCPasajeroShouldNotBeFound("edad.greaterThanOrEqual=" + (DEFAULT_EDAD + 1));
    }

    @Test
    @Transactional
    public void getAllCPasajerosByEdadIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where edad is less than or equal to DEFAULT_EDAD
        defaultCPasajeroShouldBeFound("edad.lessThanOrEqual=" + DEFAULT_EDAD);

        // Get all the cPasajeroList where edad is less than or equal to SMALLER_EDAD
        defaultCPasajeroShouldNotBeFound("edad.lessThanOrEqual=" + SMALLER_EDAD);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByEdadIsLessThanSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where edad is less than DEFAULT_EDAD
        defaultCPasajeroShouldNotBeFound("edad.lessThan=" + DEFAULT_EDAD);

        // Get all the cPasajeroList where edad is less than (DEFAULT_EDAD + 1)
        defaultCPasajeroShouldBeFound("edad.lessThan=" + (DEFAULT_EDAD + 1));
    }

    @Test
    @Transactional
    public void getAllCPasajerosByEdadIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where edad is greater than DEFAULT_EDAD
        defaultCPasajeroShouldNotBeFound("edad.greaterThan=" + DEFAULT_EDAD);

        // Get all the cPasajeroList where edad is greater than SMALLER_EDAD
        defaultCPasajeroShouldBeFound("edad.greaterThan=" + SMALLER_EDAD);
    }


    @Test
    @Transactional
    public void getAllCPasajerosByCurpIsEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where curp equals to DEFAULT_CURP
        defaultCPasajeroShouldBeFound("curp.equals=" + DEFAULT_CURP);

        // Get all the cPasajeroList where curp equals to UPDATED_CURP
        defaultCPasajeroShouldNotBeFound("curp.equals=" + UPDATED_CURP);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByCurpIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where curp not equals to DEFAULT_CURP
        defaultCPasajeroShouldNotBeFound("curp.notEquals=" + DEFAULT_CURP);

        // Get all the cPasajeroList where curp not equals to UPDATED_CURP
        defaultCPasajeroShouldBeFound("curp.notEquals=" + UPDATED_CURP);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByCurpIsInShouldWork() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where curp in DEFAULT_CURP or UPDATED_CURP
        defaultCPasajeroShouldBeFound("curp.in=" + DEFAULT_CURP + "," + UPDATED_CURP);

        // Get all the cPasajeroList where curp equals to UPDATED_CURP
        defaultCPasajeroShouldNotBeFound("curp.in=" + UPDATED_CURP);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByCurpIsNullOrNotNull() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where curp is not null
        defaultCPasajeroShouldBeFound("curp.specified=true");

        // Get all the cPasajeroList where curp is null
        defaultCPasajeroShouldNotBeFound("curp.specified=false");
    }
                @Test
    @Transactional
    public void getAllCPasajerosByCurpContainsSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where curp contains DEFAULT_CURP
        defaultCPasajeroShouldBeFound("curp.contains=" + DEFAULT_CURP);

        // Get all the cPasajeroList where curp contains UPDATED_CURP
        defaultCPasajeroShouldNotBeFound("curp.contains=" + UPDATED_CURP);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByCurpNotContainsSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where curp does not contain DEFAULT_CURP
        defaultCPasajeroShouldNotBeFound("curp.doesNotContain=" + DEFAULT_CURP);

        // Get all the cPasajeroList where curp does not contain UPDATED_CURP
        defaultCPasajeroShouldBeFound("curp.doesNotContain=" + UPDATED_CURP);
    }


    @Test
    @Transactional
    public void getAllCPasajerosByCiudadaniaIsEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where ciudadania equals to DEFAULT_CIUDADANIA
        defaultCPasajeroShouldBeFound("ciudadania.equals=" + DEFAULT_CIUDADANIA);

        // Get all the cPasajeroList where ciudadania equals to UPDATED_CIUDADANIA
        defaultCPasajeroShouldNotBeFound("ciudadania.equals=" + UPDATED_CIUDADANIA);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByCiudadaniaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where ciudadania not equals to DEFAULT_CIUDADANIA
        defaultCPasajeroShouldNotBeFound("ciudadania.notEquals=" + DEFAULT_CIUDADANIA);

        // Get all the cPasajeroList where ciudadania not equals to UPDATED_CIUDADANIA
        defaultCPasajeroShouldBeFound("ciudadania.notEquals=" + UPDATED_CIUDADANIA);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByCiudadaniaIsInShouldWork() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where ciudadania in DEFAULT_CIUDADANIA or UPDATED_CIUDADANIA
        defaultCPasajeroShouldBeFound("ciudadania.in=" + DEFAULT_CIUDADANIA + "," + UPDATED_CIUDADANIA);

        // Get all the cPasajeroList where ciudadania equals to UPDATED_CIUDADANIA
        defaultCPasajeroShouldNotBeFound("ciudadania.in=" + UPDATED_CIUDADANIA);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByCiudadaniaIsNullOrNotNull() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where ciudadania is not null
        defaultCPasajeroShouldBeFound("ciudadania.specified=true");

        // Get all the cPasajeroList where ciudadania is null
        defaultCPasajeroShouldNotBeFound("ciudadania.specified=false");
    }
                @Test
    @Transactional
    public void getAllCPasajerosByCiudadaniaContainsSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where ciudadania contains DEFAULT_CIUDADANIA
        defaultCPasajeroShouldBeFound("ciudadania.contains=" + DEFAULT_CIUDADANIA);

        // Get all the cPasajeroList where ciudadania contains UPDATED_CIUDADANIA
        defaultCPasajeroShouldNotBeFound("ciudadania.contains=" + UPDATED_CIUDADANIA);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByCiudadaniaNotContainsSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where ciudadania does not contain DEFAULT_CIUDADANIA
        defaultCPasajeroShouldNotBeFound("ciudadania.doesNotContain=" + DEFAULT_CIUDADANIA);

        // Get all the cPasajeroList where ciudadania does not contain UPDATED_CIUDADANIA
        defaultCPasajeroShouldBeFound("ciudadania.doesNotContain=" + UPDATED_CIUDADANIA);
    }


    @Test
    @Transactional
    public void getAllCPasajerosByIdUsuarioCreacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where idUsuarioCreacion equals to DEFAULT_ID_USUARIO_CREACION
        defaultCPasajeroShouldBeFound("idUsuarioCreacion.equals=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cPasajeroList where idUsuarioCreacion equals to UPDATED_ID_USUARIO_CREACION
        defaultCPasajeroShouldNotBeFound("idUsuarioCreacion.equals=" + UPDATED_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByIdUsuarioCreacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where idUsuarioCreacion not equals to DEFAULT_ID_USUARIO_CREACION
        defaultCPasajeroShouldNotBeFound("idUsuarioCreacion.notEquals=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cPasajeroList where idUsuarioCreacion not equals to UPDATED_ID_USUARIO_CREACION
        defaultCPasajeroShouldBeFound("idUsuarioCreacion.notEquals=" + UPDATED_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByIdUsuarioCreacionIsInShouldWork() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where idUsuarioCreacion in DEFAULT_ID_USUARIO_CREACION or UPDATED_ID_USUARIO_CREACION
        defaultCPasajeroShouldBeFound("idUsuarioCreacion.in=" + DEFAULT_ID_USUARIO_CREACION + "," + UPDATED_ID_USUARIO_CREACION);

        // Get all the cPasajeroList where idUsuarioCreacion equals to UPDATED_ID_USUARIO_CREACION
        defaultCPasajeroShouldNotBeFound("idUsuarioCreacion.in=" + UPDATED_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByIdUsuarioCreacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where idUsuarioCreacion is not null
        defaultCPasajeroShouldBeFound("idUsuarioCreacion.specified=true");

        // Get all the cPasajeroList where idUsuarioCreacion is null
        defaultCPasajeroShouldNotBeFound("idUsuarioCreacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCPasajerosByIdUsuarioCreacionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where idUsuarioCreacion is greater than or equal to DEFAULT_ID_USUARIO_CREACION
        defaultCPasajeroShouldBeFound("idUsuarioCreacion.greaterThanOrEqual=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cPasajeroList where idUsuarioCreacion is greater than or equal to (DEFAULT_ID_USUARIO_CREACION + 1)
        defaultCPasajeroShouldNotBeFound("idUsuarioCreacion.greaterThanOrEqual=" + (DEFAULT_ID_USUARIO_CREACION + 1));
    }

    @Test
    @Transactional
    public void getAllCPasajerosByIdUsuarioCreacionIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where idUsuarioCreacion is less than or equal to DEFAULT_ID_USUARIO_CREACION
        defaultCPasajeroShouldBeFound("idUsuarioCreacion.lessThanOrEqual=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cPasajeroList where idUsuarioCreacion is less than or equal to SMALLER_ID_USUARIO_CREACION
        defaultCPasajeroShouldNotBeFound("idUsuarioCreacion.lessThanOrEqual=" + SMALLER_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByIdUsuarioCreacionIsLessThanSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where idUsuarioCreacion is less than DEFAULT_ID_USUARIO_CREACION
        defaultCPasajeroShouldNotBeFound("idUsuarioCreacion.lessThan=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cPasajeroList where idUsuarioCreacion is less than (DEFAULT_ID_USUARIO_CREACION + 1)
        defaultCPasajeroShouldBeFound("idUsuarioCreacion.lessThan=" + (DEFAULT_ID_USUARIO_CREACION + 1));
    }

    @Test
    @Transactional
    public void getAllCPasajerosByIdUsuarioCreacionIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where idUsuarioCreacion is greater than DEFAULT_ID_USUARIO_CREACION
        defaultCPasajeroShouldNotBeFound("idUsuarioCreacion.greaterThan=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cPasajeroList where idUsuarioCreacion is greater than SMALLER_ID_USUARIO_CREACION
        defaultCPasajeroShouldBeFound("idUsuarioCreacion.greaterThan=" + SMALLER_ID_USUARIO_CREACION);
    }


    @Test
    @Transactional
    public void getAllCPasajerosByFechaCreacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where fechaCreacion equals to DEFAULT_FECHA_CREACION
        defaultCPasajeroShouldBeFound("fechaCreacion.equals=" + DEFAULT_FECHA_CREACION);

        // Get all the cPasajeroList where fechaCreacion equals to UPDATED_FECHA_CREACION
        defaultCPasajeroShouldNotBeFound("fechaCreacion.equals=" + UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByFechaCreacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where fechaCreacion not equals to DEFAULT_FECHA_CREACION
        defaultCPasajeroShouldNotBeFound("fechaCreacion.notEquals=" + DEFAULT_FECHA_CREACION);

        // Get all the cPasajeroList where fechaCreacion not equals to UPDATED_FECHA_CREACION
        defaultCPasajeroShouldBeFound("fechaCreacion.notEquals=" + UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByFechaCreacionIsInShouldWork() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where fechaCreacion in DEFAULT_FECHA_CREACION or UPDATED_FECHA_CREACION
        defaultCPasajeroShouldBeFound("fechaCreacion.in=" + DEFAULT_FECHA_CREACION + "," + UPDATED_FECHA_CREACION);

        // Get all the cPasajeroList where fechaCreacion equals to UPDATED_FECHA_CREACION
        defaultCPasajeroShouldNotBeFound("fechaCreacion.in=" + UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByFechaCreacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where fechaCreacion is not null
        defaultCPasajeroShouldBeFound("fechaCreacion.specified=true");

        // Get all the cPasajeroList where fechaCreacion is null
        defaultCPasajeroShouldNotBeFound("fechaCreacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCPasajerosByIdUsuarioActualizacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where idUsuarioActualizacion equals to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCPasajeroShouldBeFound("idUsuarioActualizacion.equals=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cPasajeroList where idUsuarioActualizacion equals to UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCPasajeroShouldNotBeFound("idUsuarioActualizacion.equals=" + UPDATED_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByIdUsuarioActualizacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where idUsuarioActualizacion not equals to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCPasajeroShouldNotBeFound("idUsuarioActualizacion.notEquals=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cPasajeroList where idUsuarioActualizacion not equals to UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCPasajeroShouldBeFound("idUsuarioActualizacion.notEquals=" + UPDATED_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByIdUsuarioActualizacionIsInShouldWork() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where idUsuarioActualizacion in DEFAULT_ID_USUARIO_ACTUALIZACION or UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCPasajeroShouldBeFound("idUsuarioActualizacion.in=" + DEFAULT_ID_USUARIO_ACTUALIZACION + "," + UPDATED_ID_USUARIO_ACTUALIZACION);

        // Get all the cPasajeroList where idUsuarioActualizacion equals to UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCPasajeroShouldNotBeFound("idUsuarioActualizacion.in=" + UPDATED_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByIdUsuarioActualizacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where idUsuarioActualizacion is not null
        defaultCPasajeroShouldBeFound("idUsuarioActualizacion.specified=true");

        // Get all the cPasajeroList where idUsuarioActualizacion is null
        defaultCPasajeroShouldNotBeFound("idUsuarioActualizacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCPasajerosByIdUsuarioActualizacionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where idUsuarioActualizacion is greater than or equal to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCPasajeroShouldBeFound("idUsuarioActualizacion.greaterThanOrEqual=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cPasajeroList where idUsuarioActualizacion is greater than or equal to (DEFAULT_ID_USUARIO_ACTUALIZACION + 1)
        defaultCPasajeroShouldNotBeFound("idUsuarioActualizacion.greaterThanOrEqual=" + (DEFAULT_ID_USUARIO_ACTUALIZACION + 1));
    }

    @Test
    @Transactional
    public void getAllCPasajerosByIdUsuarioActualizacionIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where idUsuarioActualizacion is less than or equal to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCPasajeroShouldBeFound("idUsuarioActualizacion.lessThanOrEqual=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cPasajeroList where idUsuarioActualizacion is less than or equal to SMALLER_ID_USUARIO_ACTUALIZACION
        defaultCPasajeroShouldNotBeFound("idUsuarioActualizacion.lessThanOrEqual=" + SMALLER_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByIdUsuarioActualizacionIsLessThanSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where idUsuarioActualizacion is less than DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCPasajeroShouldNotBeFound("idUsuarioActualizacion.lessThan=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cPasajeroList where idUsuarioActualizacion is less than (DEFAULT_ID_USUARIO_ACTUALIZACION + 1)
        defaultCPasajeroShouldBeFound("idUsuarioActualizacion.lessThan=" + (DEFAULT_ID_USUARIO_ACTUALIZACION + 1));
    }

    @Test
    @Transactional
    public void getAllCPasajerosByIdUsuarioActualizacionIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where idUsuarioActualizacion is greater than DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCPasajeroShouldNotBeFound("idUsuarioActualizacion.greaterThan=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cPasajeroList where idUsuarioActualizacion is greater than SMALLER_ID_USUARIO_ACTUALIZACION
        defaultCPasajeroShouldBeFound("idUsuarioActualizacion.greaterThan=" + SMALLER_ID_USUARIO_ACTUALIZACION);
    }


    @Test
    @Transactional
    public void getAllCPasajerosByFechaActualizacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where fechaActualizacion equals to DEFAULT_FECHA_ACTUALIZACION
        defaultCPasajeroShouldBeFound("fechaActualizacion.equals=" + DEFAULT_FECHA_ACTUALIZACION);

        // Get all the cPasajeroList where fechaActualizacion equals to UPDATED_FECHA_ACTUALIZACION
        defaultCPasajeroShouldNotBeFound("fechaActualizacion.equals=" + UPDATED_FECHA_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByFechaActualizacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where fechaActualizacion not equals to DEFAULT_FECHA_ACTUALIZACION
        defaultCPasajeroShouldNotBeFound("fechaActualizacion.notEquals=" + DEFAULT_FECHA_ACTUALIZACION);

        // Get all the cPasajeroList where fechaActualizacion not equals to UPDATED_FECHA_ACTUALIZACION
        defaultCPasajeroShouldBeFound("fechaActualizacion.notEquals=" + UPDATED_FECHA_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByFechaActualizacionIsInShouldWork() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where fechaActualizacion in DEFAULT_FECHA_ACTUALIZACION or UPDATED_FECHA_ACTUALIZACION
        defaultCPasajeroShouldBeFound("fechaActualizacion.in=" + DEFAULT_FECHA_ACTUALIZACION + "," + UPDATED_FECHA_ACTUALIZACION);

        // Get all the cPasajeroList where fechaActualizacion equals to UPDATED_FECHA_ACTUALIZACION
        defaultCPasajeroShouldNotBeFound("fechaActualizacion.in=" + UPDATED_FECHA_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByFechaActualizacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where fechaActualizacion is not null
        defaultCPasajeroShouldBeFound("fechaActualizacion.specified=true");

        // Get all the cPasajeroList where fechaActualizacion is null
        defaultCPasajeroShouldNotBeFound("fechaActualizacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCPasajerosByNotasIsEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where notas equals to DEFAULT_NOTAS
        defaultCPasajeroShouldBeFound("notas.equals=" + DEFAULT_NOTAS);

        // Get all the cPasajeroList where notas equals to UPDATED_NOTAS
        defaultCPasajeroShouldNotBeFound("notas.equals=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByNotasIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where notas not equals to DEFAULT_NOTAS
        defaultCPasajeroShouldNotBeFound("notas.notEquals=" + DEFAULT_NOTAS);

        // Get all the cPasajeroList where notas not equals to UPDATED_NOTAS
        defaultCPasajeroShouldBeFound("notas.notEquals=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByNotasIsInShouldWork() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where notas in DEFAULT_NOTAS or UPDATED_NOTAS
        defaultCPasajeroShouldBeFound("notas.in=" + DEFAULT_NOTAS + "," + UPDATED_NOTAS);

        // Get all the cPasajeroList where notas equals to UPDATED_NOTAS
        defaultCPasajeroShouldNotBeFound("notas.in=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByNotasIsNullOrNotNull() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where notas is not null
        defaultCPasajeroShouldBeFound("notas.specified=true");

        // Get all the cPasajeroList where notas is null
        defaultCPasajeroShouldNotBeFound("notas.specified=false");
    }
                @Test
    @Transactional
    public void getAllCPasajerosByNotasContainsSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where notas contains DEFAULT_NOTAS
        defaultCPasajeroShouldBeFound("notas.contains=" + DEFAULT_NOTAS);

        // Get all the cPasajeroList where notas contains UPDATED_NOTAS
        defaultCPasajeroShouldNotBeFound("notas.contains=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByNotasNotContainsSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where notas does not contain DEFAULT_NOTAS
        defaultCPasajeroShouldNotBeFound("notas.doesNotContain=" + DEFAULT_NOTAS);

        // Get all the cPasajeroList where notas does not contain UPDATED_NOTAS
        defaultCPasajeroShouldBeFound("notas.doesNotContain=" + UPDATED_NOTAS);
    }


    @Test
    @Transactional
    public void getAllCPasajerosByEstatusIsEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where estatus equals to DEFAULT_ESTATUS
        defaultCPasajeroShouldBeFound("estatus.equals=" + DEFAULT_ESTATUS);

        // Get all the cPasajeroList where estatus equals to UPDATED_ESTATUS
        defaultCPasajeroShouldNotBeFound("estatus.equals=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByEstatusIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where estatus not equals to DEFAULT_ESTATUS
        defaultCPasajeroShouldNotBeFound("estatus.notEquals=" + DEFAULT_ESTATUS);

        // Get all the cPasajeroList where estatus not equals to UPDATED_ESTATUS
        defaultCPasajeroShouldBeFound("estatus.notEquals=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByEstatusIsInShouldWork() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where estatus in DEFAULT_ESTATUS or UPDATED_ESTATUS
        defaultCPasajeroShouldBeFound("estatus.in=" + DEFAULT_ESTATUS + "," + UPDATED_ESTATUS);

        // Get all the cPasajeroList where estatus equals to UPDATED_ESTATUS
        defaultCPasajeroShouldNotBeFound("estatus.in=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByEstatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where estatus is not null
        defaultCPasajeroShouldBeFound("estatus.specified=true");

        // Get all the cPasajeroList where estatus is null
        defaultCPasajeroShouldNotBeFound("estatus.specified=false");
    }

    @Test
    @Transactional
    public void getAllCPasajerosByEstatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where estatus is greater than or equal to DEFAULT_ESTATUS
        defaultCPasajeroShouldBeFound("estatus.greaterThanOrEqual=" + DEFAULT_ESTATUS);

        // Get all the cPasajeroList where estatus is greater than or equal to (DEFAULT_ESTATUS + 1)
        defaultCPasajeroShouldNotBeFound("estatus.greaterThanOrEqual=" + (DEFAULT_ESTATUS + 1));
    }

    @Test
    @Transactional
    public void getAllCPasajerosByEstatusIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where estatus is less than or equal to DEFAULT_ESTATUS
        defaultCPasajeroShouldBeFound("estatus.lessThanOrEqual=" + DEFAULT_ESTATUS);

        // Get all the cPasajeroList where estatus is less than or equal to SMALLER_ESTATUS
        defaultCPasajeroShouldNotBeFound("estatus.lessThanOrEqual=" + SMALLER_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByEstatusIsLessThanSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where estatus is less than DEFAULT_ESTATUS
        defaultCPasajeroShouldNotBeFound("estatus.lessThan=" + DEFAULT_ESTATUS);

        // Get all the cPasajeroList where estatus is less than (DEFAULT_ESTATUS + 1)
        defaultCPasajeroShouldBeFound("estatus.lessThan=" + (DEFAULT_ESTATUS + 1));
    }

    @Test
    @Transactional
    public void getAllCPasajerosByEstatusIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where estatus is greater than DEFAULT_ESTATUS
        defaultCPasajeroShouldNotBeFound("estatus.greaterThan=" + DEFAULT_ESTATUS);

        // Get all the cPasajeroList where estatus is greater than SMALLER_ESTATUS
        defaultCPasajeroShouldBeFound("estatus.greaterThan=" + SMALLER_ESTATUS);
    }


    @Test
    @Transactional
    public void getAllCPasajerosByBorradoIsEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where borrado equals to DEFAULT_BORRADO
        defaultCPasajeroShouldBeFound("borrado.equals=" + DEFAULT_BORRADO);

        // Get all the cPasajeroList where borrado equals to UPDATED_BORRADO
        defaultCPasajeroShouldNotBeFound("borrado.equals=" + UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByBorradoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where borrado not equals to DEFAULT_BORRADO
        defaultCPasajeroShouldNotBeFound("borrado.notEquals=" + DEFAULT_BORRADO);

        // Get all the cPasajeroList where borrado not equals to UPDATED_BORRADO
        defaultCPasajeroShouldBeFound("borrado.notEquals=" + UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByBorradoIsInShouldWork() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where borrado in DEFAULT_BORRADO or UPDATED_BORRADO
        defaultCPasajeroShouldBeFound("borrado.in=" + DEFAULT_BORRADO + "," + UPDATED_BORRADO);

        // Get all the cPasajeroList where borrado equals to UPDATED_BORRADO
        defaultCPasajeroShouldNotBeFound("borrado.in=" + UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByBorradoIsNullOrNotNull() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where borrado is not null
        defaultCPasajeroShouldBeFound("borrado.specified=true");

        // Get all the cPasajeroList where borrado is null
        defaultCPasajeroShouldNotBeFound("borrado.specified=false");
    }

    @Test
    @Transactional
    public void getAllCPasajerosByBorradoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where borrado is greater than or equal to DEFAULT_BORRADO
        defaultCPasajeroShouldBeFound("borrado.greaterThanOrEqual=" + DEFAULT_BORRADO);

        // Get all the cPasajeroList where borrado is greater than or equal to (DEFAULT_BORRADO + 1)
        defaultCPasajeroShouldNotBeFound("borrado.greaterThanOrEqual=" + (DEFAULT_BORRADO + 1));
    }

    @Test
    @Transactional
    public void getAllCPasajerosByBorradoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where borrado is less than or equal to DEFAULT_BORRADO
        defaultCPasajeroShouldBeFound("borrado.lessThanOrEqual=" + DEFAULT_BORRADO);

        // Get all the cPasajeroList where borrado is less than or equal to SMALLER_BORRADO
        defaultCPasajeroShouldNotBeFound("borrado.lessThanOrEqual=" + SMALLER_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCPasajerosByBorradoIsLessThanSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where borrado is less than DEFAULT_BORRADO
        defaultCPasajeroShouldNotBeFound("borrado.lessThan=" + DEFAULT_BORRADO);

        // Get all the cPasajeroList where borrado is less than (DEFAULT_BORRADO + 1)
        defaultCPasajeroShouldBeFound("borrado.lessThan=" + (DEFAULT_BORRADO + 1));
    }

    @Test
    @Transactional
    public void getAllCPasajerosByBorradoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        // Get all the cPasajeroList where borrado is greater than DEFAULT_BORRADO
        defaultCPasajeroShouldNotBeFound("borrado.greaterThan=" + DEFAULT_BORRADO);

        // Get all the cPasajeroList where borrado is greater than SMALLER_BORRADO
        defaultCPasajeroShouldBeFound("borrado.greaterThan=" + SMALLER_BORRADO);
    }


    @Test
    @Transactional
    public void getAllCPasajerosByClienteIsEqualToSomething() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);
        CCliente cliente = CClienteResourceIT.createEntity(em);
        em.persist(cliente);
        em.flush();
        cPasajero.setCliente(cliente);
        cPasajeroRepository.saveAndFlush(cPasajero);
        Long clienteId = cliente.getId();

        // Get all the cPasajeroList where cliente equals to clienteId
        defaultCPasajeroShouldBeFound("clienteId.equals=" + clienteId);

        // Get all the cPasajeroList where cliente equals to clienteId + 1
        defaultCPasajeroShouldNotBeFound("clienteId.equals=" + (clienteId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCPasajeroShouldBeFound(String filter) throws Exception {
        restCPasajeroMockMvc.perform(get("/api/c-pasajeros?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cPasajero.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombreCompleto").value(hasItem(DEFAULT_NOMBRE_COMPLETO)))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].segundoNombre").value(hasItem(DEFAULT_SEGUNDO_NOMBRE)))
            .andExpect(jsonPath("$.[*].apellido").value(hasItem(DEFAULT_APELLIDO)))
            .andExpect(jsonPath("$.[*].segundoApellido").value(hasItem(DEFAULT_SEGUNDO_APELLIDO)))
            .andExpect(jsonPath("$.[*].edad").value(hasItem(DEFAULT_EDAD)))
            .andExpect(jsonPath("$.[*].curp").value(hasItem(DEFAULT_CURP)))
            .andExpect(jsonPath("$.[*].ciudadania").value(hasItem(DEFAULT_CIUDADANIA)))
            .andExpect(jsonPath("$.[*].idUsuarioCreacion").value(hasItem(DEFAULT_ID_USUARIO_CREACION.intValue())))
            .andExpect(jsonPath("$.[*].fechaCreacion").value(hasItem(DEFAULT_FECHA_CREACION.toString())))
            .andExpect(jsonPath("$.[*].idUsuarioActualizacion").value(hasItem(DEFAULT_ID_USUARIO_ACTUALIZACION.intValue())))
            .andExpect(jsonPath("$.[*].fechaActualizacion").value(hasItem(DEFAULT_FECHA_ACTUALIZACION.toString())))
            .andExpect(jsonPath("$.[*].notas").value(hasItem(DEFAULT_NOTAS)))
            .andExpect(jsonPath("$.[*].estatus").value(hasItem(DEFAULT_ESTATUS)))
            .andExpect(jsonPath("$.[*].borrado").value(hasItem(DEFAULT_BORRADO)));

        // Check, that the count call also returns 1
        restCPasajeroMockMvc.perform(get("/api/c-pasajeros/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCPasajeroShouldNotBeFound(String filter) throws Exception {
        restCPasajeroMockMvc.perform(get("/api/c-pasajeros?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCPasajeroMockMvc.perform(get("/api/c-pasajeros/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCPasajero() throws Exception {
        // Get the cPasajero
        restCPasajeroMockMvc.perform(get("/api/c-pasajeros/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCPasajero() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        int databaseSizeBeforeUpdate = cPasajeroRepository.findAll().size();

        // Update the cPasajero
        CPasajero updatedCPasajero = cPasajeroRepository.findById(cPasajero.getId()).get();
        // Disconnect from session so that the updates on updatedCPasajero are not directly saved in db
        em.detach(updatedCPasajero);
        updatedCPasajero
            .nombreCompleto(UPDATED_NOMBRE_COMPLETO)
            .nombre(UPDATED_NOMBRE)
            .segundoNombre(UPDATED_SEGUNDO_NOMBRE)
            .apellido(UPDATED_APELLIDO)
            .segundoApellido(UPDATED_SEGUNDO_APELLIDO)
            .edad(UPDATED_EDAD)
            .curp(UPDATED_CURP)
            .ciudadania(UPDATED_CIUDADANIA)
            .idUsuarioCreacion(UPDATED_ID_USUARIO_CREACION)
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .idUsuarioActualizacion(UPDATED_ID_USUARIO_ACTUALIZACION)
            .fechaActualizacion(UPDATED_FECHA_ACTUALIZACION)
            .notas(UPDATED_NOTAS)
            .estatus(UPDATED_ESTATUS)
            .borrado(UPDATED_BORRADO);
        CPasajeroDTO cPasajeroDTO = cPasajeroMapper.toDto(updatedCPasajero);

        restCPasajeroMockMvc.perform(put("/api/c-pasajeros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cPasajeroDTO)))
            .andExpect(status().isOk());

        // Validate the CPasajero in the database
        List<CPasajero> cPasajeroList = cPasajeroRepository.findAll();
        assertThat(cPasajeroList).hasSize(databaseSizeBeforeUpdate);
        CPasajero testCPasajero = cPasajeroList.get(cPasajeroList.size() - 1);
        assertThat(testCPasajero.getNombreCompleto()).isEqualTo(UPDATED_NOMBRE_COMPLETO);
        assertThat(testCPasajero.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testCPasajero.getSegundoNombre()).isEqualTo(UPDATED_SEGUNDO_NOMBRE);
        assertThat(testCPasajero.getApellido()).isEqualTo(UPDATED_APELLIDO);
        assertThat(testCPasajero.getSegundoApellido()).isEqualTo(UPDATED_SEGUNDO_APELLIDO);
        assertThat(testCPasajero.getEdad()).isEqualTo(UPDATED_EDAD);
        assertThat(testCPasajero.getCurp()).isEqualTo(UPDATED_CURP);
        assertThat(testCPasajero.getCiudadania()).isEqualTo(UPDATED_CIUDADANIA);
        assertThat(testCPasajero.getIdUsuarioCreacion()).isEqualTo(UPDATED_ID_USUARIO_CREACION);
        assertThat(testCPasajero.getFechaCreacion()).isEqualTo(UPDATED_FECHA_CREACION);
        assertThat(testCPasajero.getIdUsuarioActualizacion()).isEqualTo(UPDATED_ID_USUARIO_ACTUALIZACION);
        assertThat(testCPasajero.getFechaActualizacion()).isEqualTo(UPDATED_FECHA_ACTUALIZACION);
        assertThat(testCPasajero.getNotas()).isEqualTo(UPDATED_NOTAS);
        assertThat(testCPasajero.getEstatus()).isEqualTo(UPDATED_ESTATUS);
        assertThat(testCPasajero.getBorrado()).isEqualTo(UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void updateNonExistingCPasajero() throws Exception {
        int databaseSizeBeforeUpdate = cPasajeroRepository.findAll().size();

        // Create the CPasajero
        CPasajeroDTO cPasajeroDTO = cPasajeroMapper.toDto(cPasajero);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCPasajeroMockMvc.perform(put("/api/c-pasajeros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cPasajeroDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CPasajero in the database
        List<CPasajero> cPasajeroList = cPasajeroRepository.findAll();
        assertThat(cPasajeroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCPasajero() throws Exception {
        // Initialize the database
        cPasajeroRepository.saveAndFlush(cPasajero);

        int databaseSizeBeforeDelete = cPasajeroRepository.findAll().size();

        // Delete the cPasajero
        restCPasajeroMockMvc.perform(delete("/api/c-pasajeros/{id}", cPasajero.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CPasajero> cPasajeroList = cPasajeroRepository.findAll();
        assertThat(cPasajeroList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
