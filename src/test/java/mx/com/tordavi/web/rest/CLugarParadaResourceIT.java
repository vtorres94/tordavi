package mx.com.tordavi.web.rest;

import mx.com.tordavi.TordaviApp;
import mx.com.tordavi.domain.CLugarParada;
import mx.com.tordavi.domain.CCliente;
import mx.com.tordavi.repository.CLugarParadaRepository;
import mx.com.tordavi.service.CLugarParadaService;
import mx.com.tordavi.service.dto.CLugarParadaDTO;
import mx.com.tordavi.service.mapper.CLugarParadaMapper;
import mx.com.tordavi.service.dto.CLugarParadaCriteria;
import mx.com.tordavi.service.CLugarParadaQueryService;

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
 * Integration tests for the {@link CLugarParadaResource} REST controller.
 */
@SpringBootTest(classes = TordaviApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class CLugarParadaResourceIT {

    private static final String DEFAULT_CLAVE_LUGAR_PARADA = "AAAAAAAAAA";
    private static final String UPDATED_CLAVE_LUGAR_PARADA = "BBBBBBBBBB";

    private static final String DEFAULT_COMUNIDAD = "AAAAAAAAAA";
    private static final String UPDATED_COMUNIDAD = "BBBBBBBBBB";

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
    private CLugarParadaRepository cLugarParadaRepository;

    @Autowired
    private CLugarParadaMapper cLugarParadaMapper;

    @Autowired
    private CLugarParadaService cLugarParadaService;

    @Autowired
    private CLugarParadaQueryService cLugarParadaQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCLugarParadaMockMvc;

    private CLugarParada cLugarParada;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CLugarParada createEntity(EntityManager em) {
        CLugarParada cLugarParada = new CLugarParada()
            .claveLugarParada(DEFAULT_CLAVE_LUGAR_PARADA)
            .comunidad(DEFAULT_COMUNIDAD)
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
        return cLugarParada;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CLugarParada createUpdatedEntity(EntityManager em) {
        CLugarParada cLugarParada = new CLugarParada()
            .claveLugarParada(UPDATED_CLAVE_LUGAR_PARADA)
            .comunidad(UPDATED_COMUNIDAD)
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
        return cLugarParada;
    }

    @BeforeEach
    public void initTest() {
        cLugarParada = createEntity(em);
    }

    @Test
    @Transactional
    public void createCLugarParada() throws Exception {
        int databaseSizeBeforeCreate = cLugarParadaRepository.findAll().size();

        // Create the CLugarParada
        CLugarParadaDTO cLugarParadaDTO = cLugarParadaMapper.toDto(cLugarParada);
        restCLugarParadaMockMvc.perform(post("/api/c-lugar-paradas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cLugarParadaDTO)))
            .andExpect(status().isCreated());

        // Validate the CLugarParada in the database
        List<CLugarParada> cLugarParadaList = cLugarParadaRepository.findAll();
        assertThat(cLugarParadaList).hasSize(databaseSizeBeforeCreate + 1);
        CLugarParada testCLugarParada = cLugarParadaList.get(cLugarParadaList.size() - 1);
        assertThat(testCLugarParada.getClaveLugarParada()).isEqualTo(DEFAULT_CLAVE_LUGAR_PARADA);
        assertThat(testCLugarParada.getComunidad()).isEqualTo(DEFAULT_COMUNIDAD);
        assertThat(testCLugarParada.getCiudad()).isEqualTo(DEFAULT_CIUDAD);
        assertThat(testCLugarParada.getEstado()).isEqualTo(DEFAULT_ESTADO);
        assertThat(testCLugarParada.getPais()).isEqualTo(DEFAULT_PAIS);
        assertThat(testCLugarParada.getIdUsuarioCreacion()).isEqualTo(DEFAULT_ID_USUARIO_CREACION);
        assertThat(testCLugarParada.getFechaCreacion()).isEqualTo(DEFAULT_FECHA_CREACION);
        assertThat(testCLugarParada.getIdUsuarioActualizacion()).isEqualTo(DEFAULT_ID_USUARIO_ACTUALIZACION);
        assertThat(testCLugarParada.getFechaActualizacion()).isEqualTo(DEFAULT_FECHA_ACTUALIZACION);
        assertThat(testCLugarParada.getNotas()).isEqualTo(DEFAULT_NOTAS);
        assertThat(testCLugarParada.getEstatus()).isEqualTo(DEFAULT_ESTATUS);
        assertThat(testCLugarParada.getBorrado()).isEqualTo(DEFAULT_BORRADO);
    }

    @Test
    @Transactional
    public void createCLugarParadaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cLugarParadaRepository.findAll().size();

        // Create the CLugarParada with an existing ID
        cLugarParada.setId(1L);
        CLugarParadaDTO cLugarParadaDTO = cLugarParadaMapper.toDto(cLugarParada);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCLugarParadaMockMvc.perform(post("/api/c-lugar-paradas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cLugarParadaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CLugarParada in the database
        List<CLugarParada> cLugarParadaList = cLugarParadaRepository.findAll();
        assertThat(cLugarParadaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkClaveLugarParadaIsRequired() throws Exception {
        int databaseSizeBeforeTest = cLugarParadaRepository.findAll().size();
        // set the field null
        cLugarParada.setClaveLugarParada(null);

        // Create the CLugarParada, which fails.
        CLugarParadaDTO cLugarParadaDTO = cLugarParadaMapper.toDto(cLugarParada);

        restCLugarParadaMockMvc.perform(post("/api/c-lugar-paradas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cLugarParadaDTO)))
            .andExpect(status().isBadRequest());

        List<CLugarParada> cLugarParadaList = cLugarParadaRepository.findAll();
        assertThat(cLugarParadaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdUsuarioCreacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = cLugarParadaRepository.findAll().size();
        // set the field null
        cLugarParada.setIdUsuarioCreacion(null);

        // Create the CLugarParada, which fails.
        CLugarParadaDTO cLugarParadaDTO = cLugarParadaMapper.toDto(cLugarParada);

        restCLugarParadaMockMvc.perform(post("/api/c-lugar-paradas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cLugarParadaDTO)))
            .andExpect(status().isBadRequest());

        List<CLugarParada> cLugarParadaList = cLugarParadaRepository.findAll();
        assertThat(cLugarParadaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFechaCreacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = cLugarParadaRepository.findAll().size();
        // set the field null
        cLugarParada.setFechaCreacion(null);

        // Create the CLugarParada, which fails.
        CLugarParadaDTO cLugarParadaDTO = cLugarParadaMapper.toDto(cLugarParada);

        restCLugarParadaMockMvc.perform(post("/api/c-lugar-paradas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cLugarParadaDTO)))
            .andExpect(status().isBadRequest());

        List<CLugarParada> cLugarParadaList = cLugarParadaRepository.findAll();
        assertThat(cLugarParadaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = cLugarParadaRepository.findAll().size();
        // set the field null
        cLugarParada.setEstatus(null);

        // Create the CLugarParada, which fails.
        CLugarParadaDTO cLugarParadaDTO = cLugarParadaMapper.toDto(cLugarParada);

        restCLugarParadaMockMvc.perform(post("/api/c-lugar-paradas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cLugarParadaDTO)))
            .andExpect(status().isBadRequest());

        List<CLugarParada> cLugarParadaList = cLugarParadaRepository.findAll();
        assertThat(cLugarParadaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBorradoIsRequired() throws Exception {
        int databaseSizeBeforeTest = cLugarParadaRepository.findAll().size();
        // set the field null
        cLugarParada.setBorrado(null);

        // Create the CLugarParada, which fails.
        CLugarParadaDTO cLugarParadaDTO = cLugarParadaMapper.toDto(cLugarParada);

        restCLugarParadaMockMvc.perform(post("/api/c-lugar-paradas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cLugarParadaDTO)))
            .andExpect(status().isBadRequest());

        List<CLugarParada> cLugarParadaList = cLugarParadaRepository.findAll();
        assertThat(cLugarParadaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCLugarParadas() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList
        restCLugarParadaMockMvc.perform(get("/api/c-lugar-paradas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cLugarParada.getId().intValue())))
            .andExpect(jsonPath("$.[*].claveLugarParada").value(hasItem(DEFAULT_CLAVE_LUGAR_PARADA)))
            .andExpect(jsonPath("$.[*].comunidad").value(hasItem(DEFAULT_COMUNIDAD)))
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
    public void getCLugarParada() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get the cLugarParada
        restCLugarParadaMockMvc.perform(get("/api/c-lugar-paradas/{id}", cLugarParada.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cLugarParada.getId().intValue()))
            .andExpect(jsonPath("$.claveLugarParada").value(DEFAULT_CLAVE_LUGAR_PARADA))
            .andExpect(jsonPath("$.comunidad").value(DEFAULT_COMUNIDAD))
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
    public void getCLugarParadasByIdFiltering() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        Long id = cLugarParada.getId();

        defaultCLugarParadaShouldBeFound("id.equals=" + id);
        defaultCLugarParadaShouldNotBeFound("id.notEquals=" + id);

        defaultCLugarParadaShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCLugarParadaShouldNotBeFound("id.greaterThan=" + id);

        defaultCLugarParadaShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCLugarParadaShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllCLugarParadasByClaveLugarParadaIsEqualToSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where claveLugarParada equals to DEFAULT_CLAVE_LUGAR_PARADA
        defaultCLugarParadaShouldBeFound("claveLugarParada.equals=" + DEFAULT_CLAVE_LUGAR_PARADA);

        // Get all the cLugarParadaList where claveLugarParada equals to UPDATED_CLAVE_LUGAR_PARADA
        defaultCLugarParadaShouldNotBeFound("claveLugarParada.equals=" + UPDATED_CLAVE_LUGAR_PARADA);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByClaveLugarParadaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where claveLugarParada not equals to DEFAULT_CLAVE_LUGAR_PARADA
        defaultCLugarParadaShouldNotBeFound("claveLugarParada.notEquals=" + DEFAULT_CLAVE_LUGAR_PARADA);

        // Get all the cLugarParadaList where claveLugarParada not equals to UPDATED_CLAVE_LUGAR_PARADA
        defaultCLugarParadaShouldBeFound("claveLugarParada.notEquals=" + UPDATED_CLAVE_LUGAR_PARADA);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByClaveLugarParadaIsInShouldWork() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where claveLugarParada in DEFAULT_CLAVE_LUGAR_PARADA or UPDATED_CLAVE_LUGAR_PARADA
        defaultCLugarParadaShouldBeFound("claveLugarParada.in=" + DEFAULT_CLAVE_LUGAR_PARADA + "," + UPDATED_CLAVE_LUGAR_PARADA);

        // Get all the cLugarParadaList where claveLugarParada equals to UPDATED_CLAVE_LUGAR_PARADA
        defaultCLugarParadaShouldNotBeFound("claveLugarParada.in=" + UPDATED_CLAVE_LUGAR_PARADA);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByClaveLugarParadaIsNullOrNotNull() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where claveLugarParada is not null
        defaultCLugarParadaShouldBeFound("claveLugarParada.specified=true");

        // Get all the cLugarParadaList where claveLugarParada is null
        defaultCLugarParadaShouldNotBeFound("claveLugarParada.specified=false");
    }
                @Test
    @Transactional
    public void getAllCLugarParadasByClaveLugarParadaContainsSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where claveLugarParada contains DEFAULT_CLAVE_LUGAR_PARADA
        defaultCLugarParadaShouldBeFound("claveLugarParada.contains=" + DEFAULT_CLAVE_LUGAR_PARADA);

        // Get all the cLugarParadaList where claveLugarParada contains UPDATED_CLAVE_LUGAR_PARADA
        defaultCLugarParadaShouldNotBeFound("claveLugarParada.contains=" + UPDATED_CLAVE_LUGAR_PARADA);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByClaveLugarParadaNotContainsSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where claveLugarParada does not contain DEFAULT_CLAVE_LUGAR_PARADA
        defaultCLugarParadaShouldNotBeFound("claveLugarParada.doesNotContain=" + DEFAULT_CLAVE_LUGAR_PARADA);

        // Get all the cLugarParadaList where claveLugarParada does not contain UPDATED_CLAVE_LUGAR_PARADA
        defaultCLugarParadaShouldBeFound("claveLugarParada.doesNotContain=" + UPDATED_CLAVE_LUGAR_PARADA);
    }


    @Test
    @Transactional
    public void getAllCLugarParadasByComunidadIsEqualToSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where comunidad equals to DEFAULT_COMUNIDAD
        defaultCLugarParadaShouldBeFound("comunidad.equals=" + DEFAULT_COMUNIDAD);

        // Get all the cLugarParadaList where comunidad equals to UPDATED_COMUNIDAD
        defaultCLugarParadaShouldNotBeFound("comunidad.equals=" + UPDATED_COMUNIDAD);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByComunidadIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where comunidad not equals to DEFAULT_COMUNIDAD
        defaultCLugarParadaShouldNotBeFound("comunidad.notEquals=" + DEFAULT_COMUNIDAD);

        // Get all the cLugarParadaList where comunidad not equals to UPDATED_COMUNIDAD
        defaultCLugarParadaShouldBeFound("comunidad.notEquals=" + UPDATED_COMUNIDAD);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByComunidadIsInShouldWork() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where comunidad in DEFAULT_COMUNIDAD or UPDATED_COMUNIDAD
        defaultCLugarParadaShouldBeFound("comunidad.in=" + DEFAULT_COMUNIDAD + "," + UPDATED_COMUNIDAD);

        // Get all the cLugarParadaList where comunidad equals to UPDATED_COMUNIDAD
        defaultCLugarParadaShouldNotBeFound("comunidad.in=" + UPDATED_COMUNIDAD);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByComunidadIsNullOrNotNull() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where comunidad is not null
        defaultCLugarParadaShouldBeFound("comunidad.specified=true");

        // Get all the cLugarParadaList where comunidad is null
        defaultCLugarParadaShouldNotBeFound("comunidad.specified=false");
    }
                @Test
    @Transactional
    public void getAllCLugarParadasByComunidadContainsSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where comunidad contains DEFAULT_COMUNIDAD
        defaultCLugarParadaShouldBeFound("comunidad.contains=" + DEFAULT_COMUNIDAD);

        // Get all the cLugarParadaList where comunidad contains UPDATED_COMUNIDAD
        defaultCLugarParadaShouldNotBeFound("comunidad.contains=" + UPDATED_COMUNIDAD);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByComunidadNotContainsSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where comunidad does not contain DEFAULT_COMUNIDAD
        defaultCLugarParadaShouldNotBeFound("comunidad.doesNotContain=" + DEFAULT_COMUNIDAD);

        // Get all the cLugarParadaList where comunidad does not contain UPDATED_COMUNIDAD
        defaultCLugarParadaShouldBeFound("comunidad.doesNotContain=" + UPDATED_COMUNIDAD);
    }


    @Test
    @Transactional
    public void getAllCLugarParadasByCiudadIsEqualToSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where ciudad equals to DEFAULT_CIUDAD
        defaultCLugarParadaShouldBeFound("ciudad.equals=" + DEFAULT_CIUDAD);

        // Get all the cLugarParadaList where ciudad equals to UPDATED_CIUDAD
        defaultCLugarParadaShouldNotBeFound("ciudad.equals=" + UPDATED_CIUDAD);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByCiudadIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where ciudad not equals to DEFAULT_CIUDAD
        defaultCLugarParadaShouldNotBeFound("ciudad.notEquals=" + DEFAULT_CIUDAD);

        // Get all the cLugarParadaList where ciudad not equals to UPDATED_CIUDAD
        defaultCLugarParadaShouldBeFound("ciudad.notEquals=" + UPDATED_CIUDAD);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByCiudadIsInShouldWork() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where ciudad in DEFAULT_CIUDAD or UPDATED_CIUDAD
        defaultCLugarParadaShouldBeFound("ciudad.in=" + DEFAULT_CIUDAD + "," + UPDATED_CIUDAD);

        // Get all the cLugarParadaList where ciudad equals to UPDATED_CIUDAD
        defaultCLugarParadaShouldNotBeFound("ciudad.in=" + UPDATED_CIUDAD);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByCiudadIsNullOrNotNull() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where ciudad is not null
        defaultCLugarParadaShouldBeFound("ciudad.specified=true");

        // Get all the cLugarParadaList where ciudad is null
        defaultCLugarParadaShouldNotBeFound("ciudad.specified=false");
    }
                @Test
    @Transactional
    public void getAllCLugarParadasByCiudadContainsSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where ciudad contains DEFAULT_CIUDAD
        defaultCLugarParadaShouldBeFound("ciudad.contains=" + DEFAULT_CIUDAD);

        // Get all the cLugarParadaList where ciudad contains UPDATED_CIUDAD
        defaultCLugarParadaShouldNotBeFound("ciudad.contains=" + UPDATED_CIUDAD);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByCiudadNotContainsSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where ciudad does not contain DEFAULT_CIUDAD
        defaultCLugarParadaShouldNotBeFound("ciudad.doesNotContain=" + DEFAULT_CIUDAD);

        // Get all the cLugarParadaList where ciudad does not contain UPDATED_CIUDAD
        defaultCLugarParadaShouldBeFound("ciudad.doesNotContain=" + UPDATED_CIUDAD);
    }


    @Test
    @Transactional
    public void getAllCLugarParadasByEstadoIsEqualToSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where estado equals to DEFAULT_ESTADO
        defaultCLugarParadaShouldBeFound("estado.equals=" + DEFAULT_ESTADO);

        // Get all the cLugarParadaList where estado equals to UPDATED_ESTADO
        defaultCLugarParadaShouldNotBeFound("estado.equals=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByEstadoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where estado not equals to DEFAULT_ESTADO
        defaultCLugarParadaShouldNotBeFound("estado.notEquals=" + DEFAULT_ESTADO);

        // Get all the cLugarParadaList where estado not equals to UPDATED_ESTADO
        defaultCLugarParadaShouldBeFound("estado.notEquals=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByEstadoIsInShouldWork() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where estado in DEFAULT_ESTADO or UPDATED_ESTADO
        defaultCLugarParadaShouldBeFound("estado.in=" + DEFAULT_ESTADO + "," + UPDATED_ESTADO);

        // Get all the cLugarParadaList where estado equals to UPDATED_ESTADO
        defaultCLugarParadaShouldNotBeFound("estado.in=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByEstadoIsNullOrNotNull() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where estado is not null
        defaultCLugarParadaShouldBeFound("estado.specified=true");

        // Get all the cLugarParadaList where estado is null
        defaultCLugarParadaShouldNotBeFound("estado.specified=false");
    }
                @Test
    @Transactional
    public void getAllCLugarParadasByEstadoContainsSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where estado contains DEFAULT_ESTADO
        defaultCLugarParadaShouldBeFound("estado.contains=" + DEFAULT_ESTADO);

        // Get all the cLugarParadaList where estado contains UPDATED_ESTADO
        defaultCLugarParadaShouldNotBeFound("estado.contains=" + UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByEstadoNotContainsSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where estado does not contain DEFAULT_ESTADO
        defaultCLugarParadaShouldNotBeFound("estado.doesNotContain=" + DEFAULT_ESTADO);

        // Get all the cLugarParadaList where estado does not contain UPDATED_ESTADO
        defaultCLugarParadaShouldBeFound("estado.doesNotContain=" + UPDATED_ESTADO);
    }


    @Test
    @Transactional
    public void getAllCLugarParadasByPaisIsEqualToSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where pais equals to DEFAULT_PAIS
        defaultCLugarParadaShouldBeFound("pais.equals=" + DEFAULT_PAIS);

        // Get all the cLugarParadaList where pais equals to UPDATED_PAIS
        defaultCLugarParadaShouldNotBeFound("pais.equals=" + UPDATED_PAIS);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByPaisIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where pais not equals to DEFAULT_PAIS
        defaultCLugarParadaShouldNotBeFound("pais.notEquals=" + DEFAULT_PAIS);

        // Get all the cLugarParadaList where pais not equals to UPDATED_PAIS
        defaultCLugarParadaShouldBeFound("pais.notEquals=" + UPDATED_PAIS);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByPaisIsInShouldWork() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where pais in DEFAULT_PAIS or UPDATED_PAIS
        defaultCLugarParadaShouldBeFound("pais.in=" + DEFAULT_PAIS + "," + UPDATED_PAIS);

        // Get all the cLugarParadaList where pais equals to UPDATED_PAIS
        defaultCLugarParadaShouldNotBeFound("pais.in=" + UPDATED_PAIS);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByPaisIsNullOrNotNull() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where pais is not null
        defaultCLugarParadaShouldBeFound("pais.specified=true");

        // Get all the cLugarParadaList where pais is null
        defaultCLugarParadaShouldNotBeFound("pais.specified=false");
    }
                @Test
    @Transactional
    public void getAllCLugarParadasByPaisContainsSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where pais contains DEFAULT_PAIS
        defaultCLugarParadaShouldBeFound("pais.contains=" + DEFAULT_PAIS);

        // Get all the cLugarParadaList where pais contains UPDATED_PAIS
        defaultCLugarParadaShouldNotBeFound("pais.contains=" + UPDATED_PAIS);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByPaisNotContainsSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where pais does not contain DEFAULT_PAIS
        defaultCLugarParadaShouldNotBeFound("pais.doesNotContain=" + DEFAULT_PAIS);

        // Get all the cLugarParadaList where pais does not contain UPDATED_PAIS
        defaultCLugarParadaShouldBeFound("pais.doesNotContain=" + UPDATED_PAIS);
    }


    @Test
    @Transactional
    public void getAllCLugarParadasByIdUsuarioCreacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where idUsuarioCreacion equals to DEFAULT_ID_USUARIO_CREACION
        defaultCLugarParadaShouldBeFound("idUsuarioCreacion.equals=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cLugarParadaList where idUsuarioCreacion equals to UPDATED_ID_USUARIO_CREACION
        defaultCLugarParadaShouldNotBeFound("idUsuarioCreacion.equals=" + UPDATED_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByIdUsuarioCreacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where idUsuarioCreacion not equals to DEFAULT_ID_USUARIO_CREACION
        defaultCLugarParadaShouldNotBeFound("idUsuarioCreacion.notEquals=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cLugarParadaList where idUsuarioCreacion not equals to UPDATED_ID_USUARIO_CREACION
        defaultCLugarParadaShouldBeFound("idUsuarioCreacion.notEquals=" + UPDATED_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByIdUsuarioCreacionIsInShouldWork() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where idUsuarioCreacion in DEFAULT_ID_USUARIO_CREACION or UPDATED_ID_USUARIO_CREACION
        defaultCLugarParadaShouldBeFound("idUsuarioCreacion.in=" + DEFAULT_ID_USUARIO_CREACION + "," + UPDATED_ID_USUARIO_CREACION);

        // Get all the cLugarParadaList where idUsuarioCreacion equals to UPDATED_ID_USUARIO_CREACION
        defaultCLugarParadaShouldNotBeFound("idUsuarioCreacion.in=" + UPDATED_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByIdUsuarioCreacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where idUsuarioCreacion is not null
        defaultCLugarParadaShouldBeFound("idUsuarioCreacion.specified=true");

        // Get all the cLugarParadaList where idUsuarioCreacion is null
        defaultCLugarParadaShouldNotBeFound("idUsuarioCreacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByIdUsuarioCreacionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where idUsuarioCreacion is greater than or equal to DEFAULT_ID_USUARIO_CREACION
        defaultCLugarParadaShouldBeFound("idUsuarioCreacion.greaterThanOrEqual=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cLugarParadaList where idUsuarioCreacion is greater than or equal to (DEFAULT_ID_USUARIO_CREACION + 1)
        defaultCLugarParadaShouldNotBeFound("idUsuarioCreacion.greaterThanOrEqual=" + (DEFAULT_ID_USUARIO_CREACION + 1));
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByIdUsuarioCreacionIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where idUsuarioCreacion is less than or equal to DEFAULT_ID_USUARIO_CREACION
        defaultCLugarParadaShouldBeFound("idUsuarioCreacion.lessThanOrEqual=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cLugarParadaList where idUsuarioCreacion is less than or equal to SMALLER_ID_USUARIO_CREACION
        defaultCLugarParadaShouldNotBeFound("idUsuarioCreacion.lessThanOrEqual=" + SMALLER_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByIdUsuarioCreacionIsLessThanSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where idUsuarioCreacion is less than DEFAULT_ID_USUARIO_CREACION
        defaultCLugarParadaShouldNotBeFound("idUsuarioCreacion.lessThan=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cLugarParadaList where idUsuarioCreacion is less than (DEFAULT_ID_USUARIO_CREACION + 1)
        defaultCLugarParadaShouldBeFound("idUsuarioCreacion.lessThan=" + (DEFAULT_ID_USUARIO_CREACION + 1));
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByIdUsuarioCreacionIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where idUsuarioCreacion is greater than DEFAULT_ID_USUARIO_CREACION
        defaultCLugarParadaShouldNotBeFound("idUsuarioCreacion.greaterThan=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cLugarParadaList where idUsuarioCreacion is greater than SMALLER_ID_USUARIO_CREACION
        defaultCLugarParadaShouldBeFound("idUsuarioCreacion.greaterThan=" + SMALLER_ID_USUARIO_CREACION);
    }


    @Test
    @Transactional
    public void getAllCLugarParadasByFechaCreacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where fechaCreacion equals to DEFAULT_FECHA_CREACION
        defaultCLugarParadaShouldBeFound("fechaCreacion.equals=" + DEFAULT_FECHA_CREACION);

        // Get all the cLugarParadaList where fechaCreacion equals to UPDATED_FECHA_CREACION
        defaultCLugarParadaShouldNotBeFound("fechaCreacion.equals=" + UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByFechaCreacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where fechaCreacion not equals to DEFAULT_FECHA_CREACION
        defaultCLugarParadaShouldNotBeFound("fechaCreacion.notEquals=" + DEFAULT_FECHA_CREACION);

        // Get all the cLugarParadaList where fechaCreacion not equals to UPDATED_FECHA_CREACION
        defaultCLugarParadaShouldBeFound("fechaCreacion.notEquals=" + UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByFechaCreacionIsInShouldWork() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where fechaCreacion in DEFAULT_FECHA_CREACION or UPDATED_FECHA_CREACION
        defaultCLugarParadaShouldBeFound("fechaCreacion.in=" + DEFAULT_FECHA_CREACION + "," + UPDATED_FECHA_CREACION);

        // Get all the cLugarParadaList where fechaCreacion equals to UPDATED_FECHA_CREACION
        defaultCLugarParadaShouldNotBeFound("fechaCreacion.in=" + UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByFechaCreacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where fechaCreacion is not null
        defaultCLugarParadaShouldBeFound("fechaCreacion.specified=true");

        // Get all the cLugarParadaList where fechaCreacion is null
        defaultCLugarParadaShouldNotBeFound("fechaCreacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByIdUsuarioActualizacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where idUsuarioActualizacion equals to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCLugarParadaShouldBeFound("idUsuarioActualizacion.equals=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cLugarParadaList where idUsuarioActualizacion equals to UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCLugarParadaShouldNotBeFound("idUsuarioActualizacion.equals=" + UPDATED_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByIdUsuarioActualizacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where idUsuarioActualizacion not equals to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCLugarParadaShouldNotBeFound("idUsuarioActualizacion.notEquals=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cLugarParadaList where idUsuarioActualizacion not equals to UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCLugarParadaShouldBeFound("idUsuarioActualizacion.notEquals=" + UPDATED_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByIdUsuarioActualizacionIsInShouldWork() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where idUsuarioActualizacion in DEFAULT_ID_USUARIO_ACTUALIZACION or UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCLugarParadaShouldBeFound("idUsuarioActualizacion.in=" + DEFAULT_ID_USUARIO_ACTUALIZACION + "," + UPDATED_ID_USUARIO_ACTUALIZACION);

        // Get all the cLugarParadaList where idUsuarioActualizacion equals to UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCLugarParadaShouldNotBeFound("idUsuarioActualizacion.in=" + UPDATED_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByIdUsuarioActualizacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where idUsuarioActualizacion is not null
        defaultCLugarParadaShouldBeFound("idUsuarioActualizacion.specified=true");

        // Get all the cLugarParadaList where idUsuarioActualizacion is null
        defaultCLugarParadaShouldNotBeFound("idUsuarioActualizacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByIdUsuarioActualizacionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where idUsuarioActualizacion is greater than or equal to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCLugarParadaShouldBeFound("idUsuarioActualizacion.greaterThanOrEqual=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cLugarParadaList where idUsuarioActualizacion is greater than or equal to (DEFAULT_ID_USUARIO_ACTUALIZACION + 1)
        defaultCLugarParadaShouldNotBeFound("idUsuarioActualizacion.greaterThanOrEqual=" + (DEFAULT_ID_USUARIO_ACTUALIZACION + 1));
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByIdUsuarioActualizacionIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where idUsuarioActualizacion is less than or equal to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCLugarParadaShouldBeFound("idUsuarioActualizacion.lessThanOrEqual=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cLugarParadaList where idUsuarioActualizacion is less than or equal to SMALLER_ID_USUARIO_ACTUALIZACION
        defaultCLugarParadaShouldNotBeFound("idUsuarioActualizacion.lessThanOrEqual=" + SMALLER_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByIdUsuarioActualizacionIsLessThanSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where idUsuarioActualizacion is less than DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCLugarParadaShouldNotBeFound("idUsuarioActualizacion.lessThan=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cLugarParadaList where idUsuarioActualizacion is less than (DEFAULT_ID_USUARIO_ACTUALIZACION + 1)
        defaultCLugarParadaShouldBeFound("idUsuarioActualizacion.lessThan=" + (DEFAULT_ID_USUARIO_ACTUALIZACION + 1));
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByIdUsuarioActualizacionIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where idUsuarioActualizacion is greater than DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCLugarParadaShouldNotBeFound("idUsuarioActualizacion.greaterThan=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cLugarParadaList where idUsuarioActualizacion is greater than SMALLER_ID_USUARIO_ACTUALIZACION
        defaultCLugarParadaShouldBeFound("idUsuarioActualizacion.greaterThan=" + SMALLER_ID_USUARIO_ACTUALIZACION);
    }


    @Test
    @Transactional
    public void getAllCLugarParadasByFechaActualizacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where fechaActualizacion equals to DEFAULT_FECHA_ACTUALIZACION
        defaultCLugarParadaShouldBeFound("fechaActualizacion.equals=" + DEFAULT_FECHA_ACTUALIZACION);

        // Get all the cLugarParadaList where fechaActualizacion equals to UPDATED_FECHA_ACTUALIZACION
        defaultCLugarParadaShouldNotBeFound("fechaActualizacion.equals=" + UPDATED_FECHA_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByFechaActualizacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where fechaActualizacion not equals to DEFAULT_FECHA_ACTUALIZACION
        defaultCLugarParadaShouldNotBeFound("fechaActualizacion.notEquals=" + DEFAULT_FECHA_ACTUALIZACION);

        // Get all the cLugarParadaList where fechaActualizacion not equals to UPDATED_FECHA_ACTUALIZACION
        defaultCLugarParadaShouldBeFound("fechaActualizacion.notEquals=" + UPDATED_FECHA_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByFechaActualizacionIsInShouldWork() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where fechaActualizacion in DEFAULT_FECHA_ACTUALIZACION or UPDATED_FECHA_ACTUALIZACION
        defaultCLugarParadaShouldBeFound("fechaActualizacion.in=" + DEFAULT_FECHA_ACTUALIZACION + "," + UPDATED_FECHA_ACTUALIZACION);

        // Get all the cLugarParadaList where fechaActualizacion equals to UPDATED_FECHA_ACTUALIZACION
        defaultCLugarParadaShouldNotBeFound("fechaActualizacion.in=" + UPDATED_FECHA_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByFechaActualizacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where fechaActualizacion is not null
        defaultCLugarParadaShouldBeFound("fechaActualizacion.specified=true");

        // Get all the cLugarParadaList where fechaActualizacion is null
        defaultCLugarParadaShouldNotBeFound("fechaActualizacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByNotasIsEqualToSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where notas equals to DEFAULT_NOTAS
        defaultCLugarParadaShouldBeFound("notas.equals=" + DEFAULT_NOTAS);

        // Get all the cLugarParadaList where notas equals to UPDATED_NOTAS
        defaultCLugarParadaShouldNotBeFound("notas.equals=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByNotasIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where notas not equals to DEFAULT_NOTAS
        defaultCLugarParadaShouldNotBeFound("notas.notEquals=" + DEFAULT_NOTAS);

        // Get all the cLugarParadaList where notas not equals to UPDATED_NOTAS
        defaultCLugarParadaShouldBeFound("notas.notEquals=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByNotasIsInShouldWork() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where notas in DEFAULT_NOTAS or UPDATED_NOTAS
        defaultCLugarParadaShouldBeFound("notas.in=" + DEFAULT_NOTAS + "," + UPDATED_NOTAS);

        // Get all the cLugarParadaList where notas equals to UPDATED_NOTAS
        defaultCLugarParadaShouldNotBeFound("notas.in=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByNotasIsNullOrNotNull() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where notas is not null
        defaultCLugarParadaShouldBeFound("notas.specified=true");

        // Get all the cLugarParadaList where notas is null
        defaultCLugarParadaShouldNotBeFound("notas.specified=false");
    }
                @Test
    @Transactional
    public void getAllCLugarParadasByNotasContainsSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where notas contains DEFAULT_NOTAS
        defaultCLugarParadaShouldBeFound("notas.contains=" + DEFAULT_NOTAS);

        // Get all the cLugarParadaList where notas contains UPDATED_NOTAS
        defaultCLugarParadaShouldNotBeFound("notas.contains=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByNotasNotContainsSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where notas does not contain DEFAULT_NOTAS
        defaultCLugarParadaShouldNotBeFound("notas.doesNotContain=" + DEFAULT_NOTAS);

        // Get all the cLugarParadaList where notas does not contain UPDATED_NOTAS
        defaultCLugarParadaShouldBeFound("notas.doesNotContain=" + UPDATED_NOTAS);
    }


    @Test
    @Transactional
    public void getAllCLugarParadasByEstatusIsEqualToSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where estatus equals to DEFAULT_ESTATUS
        defaultCLugarParadaShouldBeFound("estatus.equals=" + DEFAULT_ESTATUS);

        // Get all the cLugarParadaList where estatus equals to UPDATED_ESTATUS
        defaultCLugarParadaShouldNotBeFound("estatus.equals=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByEstatusIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where estatus not equals to DEFAULT_ESTATUS
        defaultCLugarParadaShouldNotBeFound("estatus.notEquals=" + DEFAULT_ESTATUS);

        // Get all the cLugarParadaList where estatus not equals to UPDATED_ESTATUS
        defaultCLugarParadaShouldBeFound("estatus.notEquals=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByEstatusIsInShouldWork() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where estatus in DEFAULT_ESTATUS or UPDATED_ESTATUS
        defaultCLugarParadaShouldBeFound("estatus.in=" + DEFAULT_ESTATUS + "," + UPDATED_ESTATUS);

        // Get all the cLugarParadaList where estatus equals to UPDATED_ESTATUS
        defaultCLugarParadaShouldNotBeFound("estatus.in=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByEstatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where estatus is not null
        defaultCLugarParadaShouldBeFound("estatus.specified=true");

        // Get all the cLugarParadaList where estatus is null
        defaultCLugarParadaShouldNotBeFound("estatus.specified=false");
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByEstatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where estatus is greater than or equal to DEFAULT_ESTATUS
        defaultCLugarParadaShouldBeFound("estatus.greaterThanOrEqual=" + DEFAULT_ESTATUS);

        // Get all the cLugarParadaList where estatus is greater than or equal to (DEFAULT_ESTATUS + 1)
        defaultCLugarParadaShouldNotBeFound("estatus.greaterThanOrEqual=" + (DEFAULT_ESTATUS + 1));
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByEstatusIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where estatus is less than or equal to DEFAULT_ESTATUS
        defaultCLugarParadaShouldBeFound("estatus.lessThanOrEqual=" + DEFAULT_ESTATUS);

        // Get all the cLugarParadaList where estatus is less than or equal to SMALLER_ESTATUS
        defaultCLugarParadaShouldNotBeFound("estatus.lessThanOrEqual=" + SMALLER_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByEstatusIsLessThanSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where estatus is less than DEFAULT_ESTATUS
        defaultCLugarParadaShouldNotBeFound("estatus.lessThan=" + DEFAULT_ESTATUS);

        // Get all the cLugarParadaList where estatus is less than (DEFAULT_ESTATUS + 1)
        defaultCLugarParadaShouldBeFound("estatus.lessThan=" + (DEFAULT_ESTATUS + 1));
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByEstatusIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where estatus is greater than DEFAULT_ESTATUS
        defaultCLugarParadaShouldNotBeFound("estatus.greaterThan=" + DEFAULT_ESTATUS);

        // Get all the cLugarParadaList where estatus is greater than SMALLER_ESTATUS
        defaultCLugarParadaShouldBeFound("estatus.greaterThan=" + SMALLER_ESTATUS);
    }


    @Test
    @Transactional
    public void getAllCLugarParadasByBorradoIsEqualToSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where borrado equals to DEFAULT_BORRADO
        defaultCLugarParadaShouldBeFound("borrado.equals=" + DEFAULT_BORRADO);

        // Get all the cLugarParadaList where borrado equals to UPDATED_BORRADO
        defaultCLugarParadaShouldNotBeFound("borrado.equals=" + UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByBorradoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where borrado not equals to DEFAULT_BORRADO
        defaultCLugarParadaShouldNotBeFound("borrado.notEquals=" + DEFAULT_BORRADO);

        // Get all the cLugarParadaList where borrado not equals to UPDATED_BORRADO
        defaultCLugarParadaShouldBeFound("borrado.notEquals=" + UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByBorradoIsInShouldWork() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where borrado in DEFAULT_BORRADO or UPDATED_BORRADO
        defaultCLugarParadaShouldBeFound("borrado.in=" + DEFAULT_BORRADO + "," + UPDATED_BORRADO);

        // Get all the cLugarParadaList where borrado equals to UPDATED_BORRADO
        defaultCLugarParadaShouldNotBeFound("borrado.in=" + UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByBorradoIsNullOrNotNull() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where borrado is not null
        defaultCLugarParadaShouldBeFound("borrado.specified=true");

        // Get all the cLugarParadaList where borrado is null
        defaultCLugarParadaShouldNotBeFound("borrado.specified=false");
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByBorradoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where borrado is greater than or equal to DEFAULT_BORRADO
        defaultCLugarParadaShouldBeFound("borrado.greaterThanOrEqual=" + DEFAULT_BORRADO);

        // Get all the cLugarParadaList where borrado is greater than or equal to (DEFAULT_BORRADO + 1)
        defaultCLugarParadaShouldNotBeFound("borrado.greaterThanOrEqual=" + (DEFAULT_BORRADO + 1));
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByBorradoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where borrado is less than or equal to DEFAULT_BORRADO
        defaultCLugarParadaShouldBeFound("borrado.lessThanOrEqual=" + DEFAULT_BORRADO);

        // Get all the cLugarParadaList where borrado is less than or equal to SMALLER_BORRADO
        defaultCLugarParadaShouldNotBeFound("borrado.lessThanOrEqual=" + SMALLER_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByBorradoIsLessThanSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where borrado is less than DEFAULT_BORRADO
        defaultCLugarParadaShouldNotBeFound("borrado.lessThan=" + DEFAULT_BORRADO);

        // Get all the cLugarParadaList where borrado is less than (DEFAULT_BORRADO + 1)
        defaultCLugarParadaShouldBeFound("borrado.lessThan=" + (DEFAULT_BORRADO + 1));
    }

    @Test
    @Transactional
    public void getAllCLugarParadasByBorradoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        // Get all the cLugarParadaList where borrado is greater than DEFAULT_BORRADO
        defaultCLugarParadaShouldNotBeFound("borrado.greaterThan=" + DEFAULT_BORRADO);

        // Get all the cLugarParadaList where borrado is greater than SMALLER_BORRADO
        defaultCLugarParadaShouldBeFound("borrado.greaterThan=" + SMALLER_BORRADO);
    }


    @Test
    @Transactional
    public void getAllCLugarParadasByClienteIsEqualToSomething() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);
        CCliente cliente = CClienteResourceIT.createEntity(em);
        em.persist(cliente);
        em.flush();
        cLugarParada.setCliente(cliente);
        cLugarParadaRepository.saveAndFlush(cLugarParada);
        Long clienteId = cliente.getId();

        // Get all the cLugarParadaList where cliente equals to clienteId
        defaultCLugarParadaShouldBeFound("clienteId.equals=" + clienteId);

        // Get all the cLugarParadaList where cliente equals to clienteId + 1
        defaultCLugarParadaShouldNotBeFound("clienteId.equals=" + (clienteId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCLugarParadaShouldBeFound(String filter) throws Exception {
        restCLugarParadaMockMvc.perform(get("/api/c-lugar-paradas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cLugarParada.getId().intValue())))
            .andExpect(jsonPath("$.[*].claveLugarParada").value(hasItem(DEFAULT_CLAVE_LUGAR_PARADA)))
            .andExpect(jsonPath("$.[*].comunidad").value(hasItem(DEFAULT_COMUNIDAD)))
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
        restCLugarParadaMockMvc.perform(get("/api/c-lugar-paradas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCLugarParadaShouldNotBeFound(String filter) throws Exception {
        restCLugarParadaMockMvc.perform(get("/api/c-lugar-paradas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCLugarParadaMockMvc.perform(get("/api/c-lugar-paradas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCLugarParada() throws Exception {
        // Get the cLugarParada
        restCLugarParadaMockMvc.perform(get("/api/c-lugar-paradas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCLugarParada() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        int databaseSizeBeforeUpdate = cLugarParadaRepository.findAll().size();

        // Update the cLugarParada
        CLugarParada updatedCLugarParada = cLugarParadaRepository.findById(cLugarParada.getId()).get();
        // Disconnect from session so that the updates on updatedCLugarParada are not directly saved in db
        em.detach(updatedCLugarParada);
        updatedCLugarParada
            .claveLugarParada(UPDATED_CLAVE_LUGAR_PARADA)
            .comunidad(UPDATED_COMUNIDAD)
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
        CLugarParadaDTO cLugarParadaDTO = cLugarParadaMapper.toDto(updatedCLugarParada);

        restCLugarParadaMockMvc.perform(put("/api/c-lugar-paradas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cLugarParadaDTO)))
            .andExpect(status().isOk());

        // Validate the CLugarParada in the database
        List<CLugarParada> cLugarParadaList = cLugarParadaRepository.findAll();
        assertThat(cLugarParadaList).hasSize(databaseSizeBeforeUpdate);
        CLugarParada testCLugarParada = cLugarParadaList.get(cLugarParadaList.size() - 1);
        assertThat(testCLugarParada.getClaveLugarParada()).isEqualTo(UPDATED_CLAVE_LUGAR_PARADA);
        assertThat(testCLugarParada.getComunidad()).isEqualTo(UPDATED_COMUNIDAD);
        assertThat(testCLugarParada.getCiudad()).isEqualTo(UPDATED_CIUDAD);
        assertThat(testCLugarParada.getEstado()).isEqualTo(UPDATED_ESTADO);
        assertThat(testCLugarParada.getPais()).isEqualTo(UPDATED_PAIS);
        assertThat(testCLugarParada.getIdUsuarioCreacion()).isEqualTo(UPDATED_ID_USUARIO_CREACION);
        assertThat(testCLugarParada.getFechaCreacion()).isEqualTo(UPDATED_FECHA_CREACION);
        assertThat(testCLugarParada.getIdUsuarioActualizacion()).isEqualTo(UPDATED_ID_USUARIO_ACTUALIZACION);
        assertThat(testCLugarParada.getFechaActualizacion()).isEqualTo(UPDATED_FECHA_ACTUALIZACION);
        assertThat(testCLugarParada.getNotas()).isEqualTo(UPDATED_NOTAS);
        assertThat(testCLugarParada.getEstatus()).isEqualTo(UPDATED_ESTATUS);
        assertThat(testCLugarParada.getBorrado()).isEqualTo(UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void updateNonExistingCLugarParada() throws Exception {
        int databaseSizeBeforeUpdate = cLugarParadaRepository.findAll().size();

        // Create the CLugarParada
        CLugarParadaDTO cLugarParadaDTO = cLugarParadaMapper.toDto(cLugarParada);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCLugarParadaMockMvc.perform(put("/api/c-lugar-paradas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cLugarParadaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CLugarParada in the database
        List<CLugarParada> cLugarParadaList = cLugarParadaRepository.findAll();
        assertThat(cLugarParadaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCLugarParada() throws Exception {
        // Initialize the database
        cLugarParadaRepository.saveAndFlush(cLugarParada);

        int databaseSizeBeforeDelete = cLugarParadaRepository.findAll().size();

        // Delete the cLugarParada
        restCLugarParadaMockMvc.perform(delete("/api/c-lugar-paradas/{id}", cLugarParada.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CLugarParada> cLugarParadaList = cLugarParadaRepository.findAll();
        assertThat(cLugarParadaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
