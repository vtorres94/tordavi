package mx.com.tordavi.web.rest;

import mx.com.tordavi.TordaviApp;
import mx.com.tordavi.domain.CCorrida;
import mx.com.tordavi.domain.CCliente;
import mx.com.tordavi.domain.CAutobus;
import mx.com.tordavi.domain.CLugarParada;
import mx.com.tordavi.repository.CCorridaRepository;
import mx.com.tordavi.service.CCorridaService;
import mx.com.tordavi.service.dto.CCorridaDTO;
import mx.com.tordavi.service.mapper.CCorridaMapper;
import mx.com.tordavi.service.dto.CCorridaCriteria;
import mx.com.tordavi.service.CCorridaQueryService;

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
 * Integration tests for the {@link CCorridaResource} REST controller.
 */
@SpringBootTest(classes = TordaviApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class CCorridaResourceIT {

    private static final String DEFAULT_CLAVE_CORRIDA = "AAAAAAAAAA";
    private static final String UPDATED_CLAVE_CORRIDA = "BBBBBBBBBB";

    private static final String DEFAULT_HORA_SALIDA = "AAAAA";
    private static final String UPDATED_HORA_SALIDA = "BBBBB";

    private static final String DEFAULT_HORA_LLLEGADA = "AAAAA";
    private static final String UPDATED_HORA_LLLEGADA = "BBBBB";

    private static final Boolean DEFAULT_CONEXION = false;
    private static final Boolean UPDATED_CONEXION = true;

    private static final String DEFAULT_LUGAR_CONEXION = "AAAAAAAAAA";
    private static final String UPDATED_LUGAR_CONEXION = "BBBBBBBBBB";

    private static final String DEFAULT_DIAS_SALIDA = "AAAAAAAAAA";
    private static final String UPDATED_DIAS_SALIDA = "BBBBBBBBBB";

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
    private CCorridaRepository cCorridaRepository;

    @Autowired
    private CCorridaMapper cCorridaMapper;

    @Autowired
    private CCorridaService cCorridaService;

    @Autowired
    private CCorridaQueryService cCorridaQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCCorridaMockMvc;

    private CCorrida cCorrida;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CCorrida createEntity(EntityManager em) {
        CCorrida cCorrida = new CCorrida()
            .claveCorrida(DEFAULT_CLAVE_CORRIDA)
            .horaSalida(DEFAULT_HORA_SALIDA)
            .horaLllegada(DEFAULT_HORA_LLLEGADA)
            .conexion(DEFAULT_CONEXION)
            .lugarConexion(DEFAULT_LUGAR_CONEXION)
            .diasSalida(DEFAULT_DIAS_SALIDA)
            .comentarios(DEFAULT_COMENTARIOS)
            .idUsuarioCreacion(DEFAULT_ID_USUARIO_CREACION)
            .fechaCreacion(DEFAULT_FECHA_CREACION)
            .idUsuarioActualizacion(DEFAULT_ID_USUARIO_ACTUALIZACION)
            .fechaActualizacion(DEFAULT_FECHA_ACTUALIZACION)
            .notas(DEFAULT_NOTAS)
            .estatus(DEFAULT_ESTATUS)
            .borrado(DEFAULT_BORRADO);
        return cCorrida;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CCorrida createUpdatedEntity(EntityManager em) {
        CCorrida cCorrida = new CCorrida()
            .claveCorrida(UPDATED_CLAVE_CORRIDA)
            .horaSalida(UPDATED_HORA_SALIDA)
            .horaLllegada(UPDATED_HORA_LLLEGADA)
            .conexion(UPDATED_CONEXION)
            .lugarConexion(UPDATED_LUGAR_CONEXION)
            .diasSalida(UPDATED_DIAS_SALIDA)
            .comentarios(UPDATED_COMENTARIOS)
            .idUsuarioCreacion(UPDATED_ID_USUARIO_CREACION)
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .idUsuarioActualizacion(UPDATED_ID_USUARIO_ACTUALIZACION)
            .fechaActualizacion(UPDATED_FECHA_ACTUALIZACION)
            .notas(UPDATED_NOTAS)
            .estatus(UPDATED_ESTATUS)
            .borrado(UPDATED_BORRADO);
        return cCorrida;
    }

    @BeforeEach
    public void initTest() {
        cCorrida = createEntity(em);
    }

    @Test
    @Transactional
    public void createCCorrida() throws Exception {
        int databaseSizeBeforeCreate = cCorridaRepository.findAll().size();

        // Create the CCorrida
        CCorridaDTO cCorridaDTO = cCorridaMapper.toDto(cCorrida);
        restCCorridaMockMvc.perform(post("/api/c-corridas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cCorridaDTO)))
            .andExpect(status().isCreated());

        // Validate the CCorrida in the database
        List<CCorrida> cCorridaList = cCorridaRepository.findAll();
        assertThat(cCorridaList).hasSize(databaseSizeBeforeCreate + 1);
        CCorrida testCCorrida = cCorridaList.get(cCorridaList.size() - 1);
        assertThat(testCCorrida.getClaveCorrida()).isEqualTo(DEFAULT_CLAVE_CORRIDA);
        assertThat(testCCorrida.getHoraSalida()).isEqualTo(DEFAULT_HORA_SALIDA);
        assertThat(testCCorrida.getHoraLllegada()).isEqualTo(DEFAULT_HORA_LLLEGADA);
        assertThat(testCCorrida.isConexion()).isEqualTo(DEFAULT_CONEXION);
        assertThat(testCCorrida.getLugarConexion()).isEqualTo(DEFAULT_LUGAR_CONEXION);
        assertThat(testCCorrida.getDiasSalida()).isEqualTo(DEFAULT_DIAS_SALIDA);
        assertThat(testCCorrida.getComentarios()).isEqualTo(DEFAULT_COMENTARIOS);
        assertThat(testCCorrida.getIdUsuarioCreacion()).isEqualTo(DEFAULT_ID_USUARIO_CREACION);
        assertThat(testCCorrida.getFechaCreacion()).isEqualTo(DEFAULT_FECHA_CREACION);
        assertThat(testCCorrida.getIdUsuarioActualizacion()).isEqualTo(DEFAULT_ID_USUARIO_ACTUALIZACION);
        assertThat(testCCorrida.getFechaActualizacion()).isEqualTo(DEFAULT_FECHA_ACTUALIZACION);
        assertThat(testCCorrida.getNotas()).isEqualTo(DEFAULT_NOTAS);
        assertThat(testCCorrida.getEstatus()).isEqualTo(DEFAULT_ESTATUS);
        assertThat(testCCorrida.getBorrado()).isEqualTo(DEFAULT_BORRADO);
    }

    @Test
    @Transactional
    public void createCCorridaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cCorridaRepository.findAll().size();

        // Create the CCorrida with an existing ID
        cCorrida.setId(1L);
        CCorridaDTO cCorridaDTO = cCorridaMapper.toDto(cCorrida);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCCorridaMockMvc.perform(post("/api/c-corridas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cCorridaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CCorrida in the database
        List<CCorrida> cCorridaList = cCorridaRepository.findAll();
        assertThat(cCorridaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkClaveCorridaIsRequired() throws Exception {
        int databaseSizeBeforeTest = cCorridaRepository.findAll().size();
        // set the field null
        cCorrida.setClaveCorrida(null);

        // Create the CCorrida, which fails.
        CCorridaDTO cCorridaDTO = cCorridaMapper.toDto(cCorrida);

        restCCorridaMockMvc.perform(post("/api/c-corridas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cCorridaDTO)))
            .andExpect(status().isBadRequest());

        List<CCorrida> cCorridaList = cCorridaRepository.findAll();
        assertThat(cCorridaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHoraSalidaIsRequired() throws Exception {
        int databaseSizeBeforeTest = cCorridaRepository.findAll().size();
        // set the field null
        cCorrida.setHoraSalida(null);

        // Create the CCorrida, which fails.
        CCorridaDTO cCorridaDTO = cCorridaMapper.toDto(cCorrida);

        restCCorridaMockMvc.perform(post("/api/c-corridas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cCorridaDTO)))
            .andExpect(status().isBadRequest());

        List<CCorrida> cCorridaList = cCorridaRepository.findAll();
        assertThat(cCorridaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHoraLllegadaIsRequired() throws Exception {
        int databaseSizeBeforeTest = cCorridaRepository.findAll().size();
        // set the field null
        cCorrida.setHoraLllegada(null);

        // Create the CCorrida, which fails.
        CCorridaDTO cCorridaDTO = cCorridaMapper.toDto(cCorrida);

        restCCorridaMockMvc.perform(post("/api/c-corridas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cCorridaDTO)))
            .andExpect(status().isBadRequest());

        List<CCorrida> cCorridaList = cCorridaRepository.findAll();
        assertThat(cCorridaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkConexionIsRequired() throws Exception {
        int databaseSizeBeforeTest = cCorridaRepository.findAll().size();
        // set the field null
        cCorrida.setConexion(null);

        // Create the CCorrida, which fails.
        CCorridaDTO cCorridaDTO = cCorridaMapper.toDto(cCorrida);

        restCCorridaMockMvc.perform(post("/api/c-corridas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cCorridaDTO)))
            .andExpect(status().isBadRequest());

        List<CCorrida> cCorridaList = cCorridaRepository.findAll();
        assertThat(cCorridaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdUsuarioCreacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = cCorridaRepository.findAll().size();
        // set the field null
        cCorrida.setIdUsuarioCreacion(null);

        // Create the CCorrida, which fails.
        CCorridaDTO cCorridaDTO = cCorridaMapper.toDto(cCorrida);

        restCCorridaMockMvc.perform(post("/api/c-corridas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cCorridaDTO)))
            .andExpect(status().isBadRequest());

        List<CCorrida> cCorridaList = cCorridaRepository.findAll();
        assertThat(cCorridaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFechaCreacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = cCorridaRepository.findAll().size();
        // set the field null
        cCorrida.setFechaCreacion(null);

        // Create the CCorrida, which fails.
        CCorridaDTO cCorridaDTO = cCorridaMapper.toDto(cCorrida);

        restCCorridaMockMvc.perform(post("/api/c-corridas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cCorridaDTO)))
            .andExpect(status().isBadRequest());

        List<CCorrida> cCorridaList = cCorridaRepository.findAll();
        assertThat(cCorridaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = cCorridaRepository.findAll().size();
        // set the field null
        cCorrida.setEstatus(null);

        // Create the CCorrida, which fails.
        CCorridaDTO cCorridaDTO = cCorridaMapper.toDto(cCorrida);

        restCCorridaMockMvc.perform(post("/api/c-corridas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cCorridaDTO)))
            .andExpect(status().isBadRequest());

        List<CCorrida> cCorridaList = cCorridaRepository.findAll();
        assertThat(cCorridaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBorradoIsRequired() throws Exception {
        int databaseSizeBeforeTest = cCorridaRepository.findAll().size();
        // set the field null
        cCorrida.setBorrado(null);

        // Create the CCorrida, which fails.
        CCorridaDTO cCorridaDTO = cCorridaMapper.toDto(cCorrida);

        restCCorridaMockMvc.perform(post("/api/c-corridas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cCorridaDTO)))
            .andExpect(status().isBadRequest());

        List<CCorrida> cCorridaList = cCorridaRepository.findAll();
        assertThat(cCorridaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCCorridas() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList
        restCCorridaMockMvc.perform(get("/api/c-corridas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cCorrida.getId().intValue())))
            .andExpect(jsonPath("$.[*].claveCorrida").value(hasItem(DEFAULT_CLAVE_CORRIDA)))
            .andExpect(jsonPath("$.[*].horaSalida").value(hasItem(DEFAULT_HORA_SALIDA)))
            .andExpect(jsonPath("$.[*].horaLllegada").value(hasItem(DEFAULT_HORA_LLLEGADA)))
            .andExpect(jsonPath("$.[*].conexion").value(hasItem(DEFAULT_CONEXION.booleanValue())))
            .andExpect(jsonPath("$.[*].lugarConexion").value(hasItem(DEFAULT_LUGAR_CONEXION)))
            .andExpect(jsonPath("$.[*].diasSalida").value(hasItem(DEFAULT_DIAS_SALIDA)))
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
    public void getCCorrida() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get the cCorrida
        restCCorridaMockMvc.perform(get("/api/c-corridas/{id}", cCorrida.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cCorrida.getId().intValue()))
            .andExpect(jsonPath("$.claveCorrida").value(DEFAULT_CLAVE_CORRIDA))
            .andExpect(jsonPath("$.horaSalida").value(DEFAULT_HORA_SALIDA))
            .andExpect(jsonPath("$.horaLllegada").value(DEFAULT_HORA_LLLEGADA))
            .andExpect(jsonPath("$.conexion").value(DEFAULT_CONEXION.booleanValue()))
            .andExpect(jsonPath("$.lugarConexion").value(DEFAULT_LUGAR_CONEXION))
            .andExpect(jsonPath("$.diasSalida").value(DEFAULT_DIAS_SALIDA))
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
    public void getCCorridasByIdFiltering() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        Long id = cCorrida.getId();

        defaultCCorridaShouldBeFound("id.equals=" + id);
        defaultCCorridaShouldNotBeFound("id.notEquals=" + id);

        defaultCCorridaShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCCorridaShouldNotBeFound("id.greaterThan=" + id);

        defaultCCorridaShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCCorridaShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllCCorridasByClaveCorridaIsEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where claveCorrida equals to DEFAULT_CLAVE_CORRIDA
        defaultCCorridaShouldBeFound("claveCorrida.equals=" + DEFAULT_CLAVE_CORRIDA);

        // Get all the cCorridaList where claveCorrida equals to UPDATED_CLAVE_CORRIDA
        defaultCCorridaShouldNotBeFound("claveCorrida.equals=" + UPDATED_CLAVE_CORRIDA);
    }

    @Test
    @Transactional
    public void getAllCCorridasByClaveCorridaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where claveCorrida not equals to DEFAULT_CLAVE_CORRIDA
        defaultCCorridaShouldNotBeFound("claveCorrida.notEquals=" + DEFAULT_CLAVE_CORRIDA);

        // Get all the cCorridaList where claveCorrida not equals to UPDATED_CLAVE_CORRIDA
        defaultCCorridaShouldBeFound("claveCorrida.notEquals=" + UPDATED_CLAVE_CORRIDA);
    }

    @Test
    @Transactional
    public void getAllCCorridasByClaveCorridaIsInShouldWork() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where claveCorrida in DEFAULT_CLAVE_CORRIDA or UPDATED_CLAVE_CORRIDA
        defaultCCorridaShouldBeFound("claveCorrida.in=" + DEFAULT_CLAVE_CORRIDA + "," + UPDATED_CLAVE_CORRIDA);

        // Get all the cCorridaList where claveCorrida equals to UPDATED_CLAVE_CORRIDA
        defaultCCorridaShouldNotBeFound("claveCorrida.in=" + UPDATED_CLAVE_CORRIDA);
    }

    @Test
    @Transactional
    public void getAllCCorridasByClaveCorridaIsNullOrNotNull() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where claveCorrida is not null
        defaultCCorridaShouldBeFound("claveCorrida.specified=true");

        // Get all the cCorridaList where claveCorrida is null
        defaultCCorridaShouldNotBeFound("claveCorrida.specified=false");
    }
                @Test
    @Transactional
    public void getAllCCorridasByClaveCorridaContainsSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where claveCorrida contains DEFAULT_CLAVE_CORRIDA
        defaultCCorridaShouldBeFound("claveCorrida.contains=" + DEFAULT_CLAVE_CORRIDA);

        // Get all the cCorridaList where claveCorrida contains UPDATED_CLAVE_CORRIDA
        defaultCCorridaShouldNotBeFound("claveCorrida.contains=" + UPDATED_CLAVE_CORRIDA);
    }

    @Test
    @Transactional
    public void getAllCCorridasByClaveCorridaNotContainsSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where claveCorrida does not contain DEFAULT_CLAVE_CORRIDA
        defaultCCorridaShouldNotBeFound("claveCorrida.doesNotContain=" + DEFAULT_CLAVE_CORRIDA);

        // Get all the cCorridaList where claveCorrida does not contain UPDATED_CLAVE_CORRIDA
        defaultCCorridaShouldBeFound("claveCorrida.doesNotContain=" + UPDATED_CLAVE_CORRIDA);
    }


    @Test
    @Transactional
    public void getAllCCorridasByHoraSalidaIsEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where horaSalida equals to DEFAULT_HORA_SALIDA
        defaultCCorridaShouldBeFound("horaSalida.equals=" + DEFAULT_HORA_SALIDA);

        // Get all the cCorridaList where horaSalida equals to UPDATED_HORA_SALIDA
        defaultCCorridaShouldNotBeFound("horaSalida.equals=" + UPDATED_HORA_SALIDA);
    }

    @Test
    @Transactional
    public void getAllCCorridasByHoraSalidaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where horaSalida not equals to DEFAULT_HORA_SALIDA
        defaultCCorridaShouldNotBeFound("horaSalida.notEquals=" + DEFAULT_HORA_SALIDA);

        // Get all the cCorridaList where horaSalida not equals to UPDATED_HORA_SALIDA
        defaultCCorridaShouldBeFound("horaSalida.notEquals=" + UPDATED_HORA_SALIDA);
    }

    @Test
    @Transactional
    public void getAllCCorridasByHoraSalidaIsInShouldWork() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where horaSalida in DEFAULT_HORA_SALIDA or UPDATED_HORA_SALIDA
        defaultCCorridaShouldBeFound("horaSalida.in=" + DEFAULT_HORA_SALIDA + "," + UPDATED_HORA_SALIDA);

        // Get all the cCorridaList where horaSalida equals to UPDATED_HORA_SALIDA
        defaultCCorridaShouldNotBeFound("horaSalida.in=" + UPDATED_HORA_SALIDA);
    }

    @Test
    @Transactional
    public void getAllCCorridasByHoraSalidaIsNullOrNotNull() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where horaSalida is not null
        defaultCCorridaShouldBeFound("horaSalida.specified=true");

        // Get all the cCorridaList where horaSalida is null
        defaultCCorridaShouldNotBeFound("horaSalida.specified=false");
    }
                @Test
    @Transactional
    public void getAllCCorridasByHoraSalidaContainsSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where horaSalida contains DEFAULT_HORA_SALIDA
        defaultCCorridaShouldBeFound("horaSalida.contains=" + DEFAULT_HORA_SALIDA);

        // Get all the cCorridaList where horaSalida contains UPDATED_HORA_SALIDA
        defaultCCorridaShouldNotBeFound("horaSalida.contains=" + UPDATED_HORA_SALIDA);
    }

    @Test
    @Transactional
    public void getAllCCorridasByHoraSalidaNotContainsSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where horaSalida does not contain DEFAULT_HORA_SALIDA
        defaultCCorridaShouldNotBeFound("horaSalida.doesNotContain=" + DEFAULT_HORA_SALIDA);

        // Get all the cCorridaList where horaSalida does not contain UPDATED_HORA_SALIDA
        defaultCCorridaShouldBeFound("horaSalida.doesNotContain=" + UPDATED_HORA_SALIDA);
    }


    @Test
    @Transactional
    public void getAllCCorridasByHoraLllegadaIsEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where horaLllegada equals to DEFAULT_HORA_LLLEGADA
        defaultCCorridaShouldBeFound("horaLllegada.equals=" + DEFAULT_HORA_LLLEGADA);

        // Get all the cCorridaList where horaLllegada equals to UPDATED_HORA_LLLEGADA
        defaultCCorridaShouldNotBeFound("horaLllegada.equals=" + UPDATED_HORA_LLLEGADA);
    }

    @Test
    @Transactional
    public void getAllCCorridasByHoraLllegadaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where horaLllegada not equals to DEFAULT_HORA_LLLEGADA
        defaultCCorridaShouldNotBeFound("horaLllegada.notEquals=" + DEFAULT_HORA_LLLEGADA);

        // Get all the cCorridaList where horaLllegada not equals to UPDATED_HORA_LLLEGADA
        defaultCCorridaShouldBeFound("horaLllegada.notEquals=" + UPDATED_HORA_LLLEGADA);
    }

    @Test
    @Transactional
    public void getAllCCorridasByHoraLllegadaIsInShouldWork() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where horaLllegada in DEFAULT_HORA_LLLEGADA or UPDATED_HORA_LLLEGADA
        defaultCCorridaShouldBeFound("horaLllegada.in=" + DEFAULT_HORA_LLLEGADA + "," + UPDATED_HORA_LLLEGADA);

        // Get all the cCorridaList where horaLllegada equals to UPDATED_HORA_LLLEGADA
        defaultCCorridaShouldNotBeFound("horaLllegada.in=" + UPDATED_HORA_LLLEGADA);
    }

    @Test
    @Transactional
    public void getAllCCorridasByHoraLllegadaIsNullOrNotNull() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where horaLllegada is not null
        defaultCCorridaShouldBeFound("horaLllegada.specified=true");

        // Get all the cCorridaList where horaLllegada is null
        defaultCCorridaShouldNotBeFound("horaLllegada.specified=false");
    }
                @Test
    @Transactional
    public void getAllCCorridasByHoraLllegadaContainsSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where horaLllegada contains DEFAULT_HORA_LLLEGADA
        defaultCCorridaShouldBeFound("horaLllegada.contains=" + DEFAULT_HORA_LLLEGADA);

        // Get all the cCorridaList where horaLllegada contains UPDATED_HORA_LLLEGADA
        defaultCCorridaShouldNotBeFound("horaLllegada.contains=" + UPDATED_HORA_LLLEGADA);
    }

    @Test
    @Transactional
    public void getAllCCorridasByHoraLllegadaNotContainsSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where horaLllegada does not contain DEFAULT_HORA_LLLEGADA
        defaultCCorridaShouldNotBeFound("horaLllegada.doesNotContain=" + DEFAULT_HORA_LLLEGADA);

        // Get all the cCorridaList where horaLllegada does not contain UPDATED_HORA_LLLEGADA
        defaultCCorridaShouldBeFound("horaLllegada.doesNotContain=" + UPDATED_HORA_LLLEGADA);
    }


    @Test
    @Transactional
    public void getAllCCorridasByConexionIsEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where conexion equals to DEFAULT_CONEXION
        defaultCCorridaShouldBeFound("conexion.equals=" + DEFAULT_CONEXION);

        // Get all the cCorridaList where conexion equals to UPDATED_CONEXION
        defaultCCorridaShouldNotBeFound("conexion.equals=" + UPDATED_CONEXION);
    }

    @Test
    @Transactional
    public void getAllCCorridasByConexionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where conexion not equals to DEFAULT_CONEXION
        defaultCCorridaShouldNotBeFound("conexion.notEquals=" + DEFAULT_CONEXION);

        // Get all the cCorridaList where conexion not equals to UPDATED_CONEXION
        defaultCCorridaShouldBeFound("conexion.notEquals=" + UPDATED_CONEXION);
    }

    @Test
    @Transactional
    public void getAllCCorridasByConexionIsInShouldWork() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where conexion in DEFAULT_CONEXION or UPDATED_CONEXION
        defaultCCorridaShouldBeFound("conexion.in=" + DEFAULT_CONEXION + "," + UPDATED_CONEXION);

        // Get all the cCorridaList where conexion equals to UPDATED_CONEXION
        defaultCCorridaShouldNotBeFound("conexion.in=" + UPDATED_CONEXION);
    }

    @Test
    @Transactional
    public void getAllCCorridasByConexionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where conexion is not null
        defaultCCorridaShouldBeFound("conexion.specified=true");

        // Get all the cCorridaList where conexion is null
        defaultCCorridaShouldNotBeFound("conexion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCCorridasByLugarConexionIsEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where lugarConexion equals to DEFAULT_LUGAR_CONEXION
        defaultCCorridaShouldBeFound("lugarConexion.equals=" + DEFAULT_LUGAR_CONEXION);

        // Get all the cCorridaList where lugarConexion equals to UPDATED_LUGAR_CONEXION
        defaultCCorridaShouldNotBeFound("lugarConexion.equals=" + UPDATED_LUGAR_CONEXION);
    }

    @Test
    @Transactional
    public void getAllCCorridasByLugarConexionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where lugarConexion not equals to DEFAULT_LUGAR_CONEXION
        defaultCCorridaShouldNotBeFound("lugarConexion.notEquals=" + DEFAULT_LUGAR_CONEXION);

        // Get all the cCorridaList where lugarConexion not equals to UPDATED_LUGAR_CONEXION
        defaultCCorridaShouldBeFound("lugarConexion.notEquals=" + UPDATED_LUGAR_CONEXION);
    }

    @Test
    @Transactional
    public void getAllCCorridasByLugarConexionIsInShouldWork() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where lugarConexion in DEFAULT_LUGAR_CONEXION or UPDATED_LUGAR_CONEXION
        defaultCCorridaShouldBeFound("lugarConexion.in=" + DEFAULT_LUGAR_CONEXION + "," + UPDATED_LUGAR_CONEXION);

        // Get all the cCorridaList where lugarConexion equals to UPDATED_LUGAR_CONEXION
        defaultCCorridaShouldNotBeFound("lugarConexion.in=" + UPDATED_LUGAR_CONEXION);
    }

    @Test
    @Transactional
    public void getAllCCorridasByLugarConexionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where lugarConexion is not null
        defaultCCorridaShouldBeFound("lugarConexion.specified=true");

        // Get all the cCorridaList where lugarConexion is null
        defaultCCorridaShouldNotBeFound("lugarConexion.specified=false");
    }
                @Test
    @Transactional
    public void getAllCCorridasByLugarConexionContainsSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where lugarConexion contains DEFAULT_LUGAR_CONEXION
        defaultCCorridaShouldBeFound("lugarConexion.contains=" + DEFAULT_LUGAR_CONEXION);

        // Get all the cCorridaList where lugarConexion contains UPDATED_LUGAR_CONEXION
        defaultCCorridaShouldNotBeFound("lugarConexion.contains=" + UPDATED_LUGAR_CONEXION);
    }

    @Test
    @Transactional
    public void getAllCCorridasByLugarConexionNotContainsSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where lugarConexion does not contain DEFAULT_LUGAR_CONEXION
        defaultCCorridaShouldNotBeFound("lugarConexion.doesNotContain=" + DEFAULT_LUGAR_CONEXION);

        // Get all the cCorridaList where lugarConexion does not contain UPDATED_LUGAR_CONEXION
        defaultCCorridaShouldBeFound("lugarConexion.doesNotContain=" + UPDATED_LUGAR_CONEXION);
    }


    @Test
    @Transactional
    public void getAllCCorridasByDiasSalidaIsEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where diasSalida equals to DEFAULT_DIAS_SALIDA
        defaultCCorridaShouldBeFound("diasSalida.equals=" + DEFAULT_DIAS_SALIDA);

        // Get all the cCorridaList where diasSalida equals to UPDATED_DIAS_SALIDA
        defaultCCorridaShouldNotBeFound("diasSalida.equals=" + UPDATED_DIAS_SALIDA);
    }

    @Test
    @Transactional
    public void getAllCCorridasByDiasSalidaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where diasSalida not equals to DEFAULT_DIAS_SALIDA
        defaultCCorridaShouldNotBeFound("diasSalida.notEquals=" + DEFAULT_DIAS_SALIDA);

        // Get all the cCorridaList where diasSalida not equals to UPDATED_DIAS_SALIDA
        defaultCCorridaShouldBeFound("diasSalida.notEquals=" + UPDATED_DIAS_SALIDA);
    }

    @Test
    @Transactional
    public void getAllCCorridasByDiasSalidaIsInShouldWork() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where diasSalida in DEFAULT_DIAS_SALIDA or UPDATED_DIAS_SALIDA
        defaultCCorridaShouldBeFound("diasSalida.in=" + DEFAULT_DIAS_SALIDA + "," + UPDATED_DIAS_SALIDA);

        // Get all the cCorridaList where diasSalida equals to UPDATED_DIAS_SALIDA
        defaultCCorridaShouldNotBeFound("diasSalida.in=" + UPDATED_DIAS_SALIDA);
    }

    @Test
    @Transactional
    public void getAllCCorridasByDiasSalidaIsNullOrNotNull() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where diasSalida is not null
        defaultCCorridaShouldBeFound("diasSalida.specified=true");

        // Get all the cCorridaList where diasSalida is null
        defaultCCorridaShouldNotBeFound("diasSalida.specified=false");
    }
                @Test
    @Transactional
    public void getAllCCorridasByDiasSalidaContainsSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where diasSalida contains DEFAULT_DIAS_SALIDA
        defaultCCorridaShouldBeFound("diasSalida.contains=" + DEFAULT_DIAS_SALIDA);

        // Get all the cCorridaList where diasSalida contains UPDATED_DIAS_SALIDA
        defaultCCorridaShouldNotBeFound("diasSalida.contains=" + UPDATED_DIAS_SALIDA);
    }

    @Test
    @Transactional
    public void getAllCCorridasByDiasSalidaNotContainsSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where diasSalida does not contain DEFAULT_DIAS_SALIDA
        defaultCCorridaShouldNotBeFound("diasSalida.doesNotContain=" + DEFAULT_DIAS_SALIDA);

        // Get all the cCorridaList where diasSalida does not contain UPDATED_DIAS_SALIDA
        defaultCCorridaShouldBeFound("diasSalida.doesNotContain=" + UPDATED_DIAS_SALIDA);
    }


    @Test
    @Transactional
    public void getAllCCorridasByComentariosIsEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where comentarios equals to DEFAULT_COMENTARIOS
        defaultCCorridaShouldBeFound("comentarios.equals=" + DEFAULT_COMENTARIOS);

        // Get all the cCorridaList where comentarios equals to UPDATED_COMENTARIOS
        defaultCCorridaShouldNotBeFound("comentarios.equals=" + UPDATED_COMENTARIOS);
    }

    @Test
    @Transactional
    public void getAllCCorridasByComentariosIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where comentarios not equals to DEFAULT_COMENTARIOS
        defaultCCorridaShouldNotBeFound("comentarios.notEquals=" + DEFAULT_COMENTARIOS);

        // Get all the cCorridaList where comentarios not equals to UPDATED_COMENTARIOS
        defaultCCorridaShouldBeFound("comentarios.notEquals=" + UPDATED_COMENTARIOS);
    }

    @Test
    @Transactional
    public void getAllCCorridasByComentariosIsInShouldWork() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where comentarios in DEFAULT_COMENTARIOS or UPDATED_COMENTARIOS
        defaultCCorridaShouldBeFound("comentarios.in=" + DEFAULT_COMENTARIOS + "," + UPDATED_COMENTARIOS);

        // Get all the cCorridaList where comentarios equals to UPDATED_COMENTARIOS
        defaultCCorridaShouldNotBeFound("comentarios.in=" + UPDATED_COMENTARIOS);
    }

    @Test
    @Transactional
    public void getAllCCorridasByComentariosIsNullOrNotNull() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where comentarios is not null
        defaultCCorridaShouldBeFound("comentarios.specified=true");

        // Get all the cCorridaList where comentarios is null
        defaultCCorridaShouldNotBeFound("comentarios.specified=false");
    }
                @Test
    @Transactional
    public void getAllCCorridasByComentariosContainsSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where comentarios contains DEFAULT_COMENTARIOS
        defaultCCorridaShouldBeFound("comentarios.contains=" + DEFAULT_COMENTARIOS);

        // Get all the cCorridaList where comentarios contains UPDATED_COMENTARIOS
        defaultCCorridaShouldNotBeFound("comentarios.contains=" + UPDATED_COMENTARIOS);
    }

    @Test
    @Transactional
    public void getAllCCorridasByComentariosNotContainsSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where comentarios does not contain DEFAULT_COMENTARIOS
        defaultCCorridaShouldNotBeFound("comentarios.doesNotContain=" + DEFAULT_COMENTARIOS);

        // Get all the cCorridaList where comentarios does not contain UPDATED_COMENTARIOS
        defaultCCorridaShouldBeFound("comentarios.doesNotContain=" + UPDATED_COMENTARIOS);
    }


    @Test
    @Transactional
    public void getAllCCorridasByIdUsuarioCreacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where idUsuarioCreacion equals to DEFAULT_ID_USUARIO_CREACION
        defaultCCorridaShouldBeFound("idUsuarioCreacion.equals=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cCorridaList where idUsuarioCreacion equals to UPDATED_ID_USUARIO_CREACION
        defaultCCorridaShouldNotBeFound("idUsuarioCreacion.equals=" + UPDATED_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCCorridasByIdUsuarioCreacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where idUsuarioCreacion not equals to DEFAULT_ID_USUARIO_CREACION
        defaultCCorridaShouldNotBeFound("idUsuarioCreacion.notEquals=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cCorridaList where idUsuarioCreacion not equals to UPDATED_ID_USUARIO_CREACION
        defaultCCorridaShouldBeFound("idUsuarioCreacion.notEquals=" + UPDATED_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCCorridasByIdUsuarioCreacionIsInShouldWork() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where idUsuarioCreacion in DEFAULT_ID_USUARIO_CREACION or UPDATED_ID_USUARIO_CREACION
        defaultCCorridaShouldBeFound("idUsuarioCreacion.in=" + DEFAULT_ID_USUARIO_CREACION + "," + UPDATED_ID_USUARIO_CREACION);

        // Get all the cCorridaList where idUsuarioCreacion equals to UPDATED_ID_USUARIO_CREACION
        defaultCCorridaShouldNotBeFound("idUsuarioCreacion.in=" + UPDATED_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCCorridasByIdUsuarioCreacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where idUsuarioCreacion is not null
        defaultCCorridaShouldBeFound("idUsuarioCreacion.specified=true");

        // Get all the cCorridaList where idUsuarioCreacion is null
        defaultCCorridaShouldNotBeFound("idUsuarioCreacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCCorridasByIdUsuarioCreacionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where idUsuarioCreacion is greater than or equal to DEFAULT_ID_USUARIO_CREACION
        defaultCCorridaShouldBeFound("idUsuarioCreacion.greaterThanOrEqual=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cCorridaList where idUsuarioCreacion is greater than or equal to (DEFAULT_ID_USUARIO_CREACION + 1)
        defaultCCorridaShouldNotBeFound("idUsuarioCreacion.greaterThanOrEqual=" + (DEFAULT_ID_USUARIO_CREACION + 1));
    }

    @Test
    @Transactional
    public void getAllCCorridasByIdUsuarioCreacionIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where idUsuarioCreacion is less than or equal to DEFAULT_ID_USUARIO_CREACION
        defaultCCorridaShouldBeFound("idUsuarioCreacion.lessThanOrEqual=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cCorridaList where idUsuarioCreacion is less than or equal to SMALLER_ID_USUARIO_CREACION
        defaultCCorridaShouldNotBeFound("idUsuarioCreacion.lessThanOrEqual=" + SMALLER_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCCorridasByIdUsuarioCreacionIsLessThanSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where idUsuarioCreacion is less than DEFAULT_ID_USUARIO_CREACION
        defaultCCorridaShouldNotBeFound("idUsuarioCreacion.lessThan=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cCorridaList where idUsuarioCreacion is less than (DEFAULT_ID_USUARIO_CREACION + 1)
        defaultCCorridaShouldBeFound("idUsuarioCreacion.lessThan=" + (DEFAULT_ID_USUARIO_CREACION + 1));
    }

    @Test
    @Transactional
    public void getAllCCorridasByIdUsuarioCreacionIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where idUsuarioCreacion is greater than DEFAULT_ID_USUARIO_CREACION
        defaultCCorridaShouldNotBeFound("idUsuarioCreacion.greaterThan=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cCorridaList where idUsuarioCreacion is greater than SMALLER_ID_USUARIO_CREACION
        defaultCCorridaShouldBeFound("idUsuarioCreacion.greaterThan=" + SMALLER_ID_USUARIO_CREACION);
    }


    @Test
    @Transactional
    public void getAllCCorridasByFechaCreacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where fechaCreacion equals to DEFAULT_FECHA_CREACION
        defaultCCorridaShouldBeFound("fechaCreacion.equals=" + DEFAULT_FECHA_CREACION);

        // Get all the cCorridaList where fechaCreacion equals to UPDATED_FECHA_CREACION
        defaultCCorridaShouldNotBeFound("fechaCreacion.equals=" + UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void getAllCCorridasByFechaCreacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where fechaCreacion not equals to DEFAULT_FECHA_CREACION
        defaultCCorridaShouldNotBeFound("fechaCreacion.notEquals=" + DEFAULT_FECHA_CREACION);

        // Get all the cCorridaList where fechaCreacion not equals to UPDATED_FECHA_CREACION
        defaultCCorridaShouldBeFound("fechaCreacion.notEquals=" + UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void getAllCCorridasByFechaCreacionIsInShouldWork() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where fechaCreacion in DEFAULT_FECHA_CREACION or UPDATED_FECHA_CREACION
        defaultCCorridaShouldBeFound("fechaCreacion.in=" + DEFAULT_FECHA_CREACION + "," + UPDATED_FECHA_CREACION);

        // Get all the cCorridaList where fechaCreacion equals to UPDATED_FECHA_CREACION
        defaultCCorridaShouldNotBeFound("fechaCreacion.in=" + UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void getAllCCorridasByFechaCreacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where fechaCreacion is not null
        defaultCCorridaShouldBeFound("fechaCreacion.specified=true");

        // Get all the cCorridaList where fechaCreacion is null
        defaultCCorridaShouldNotBeFound("fechaCreacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCCorridasByIdUsuarioActualizacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where idUsuarioActualizacion equals to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCCorridaShouldBeFound("idUsuarioActualizacion.equals=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cCorridaList where idUsuarioActualizacion equals to UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCCorridaShouldNotBeFound("idUsuarioActualizacion.equals=" + UPDATED_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCCorridasByIdUsuarioActualizacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where idUsuarioActualizacion not equals to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCCorridaShouldNotBeFound("idUsuarioActualizacion.notEquals=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cCorridaList where idUsuarioActualizacion not equals to UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCCorridaShouldBeFound("idUsuarioActualizacion.notEquals=" + UPDATED_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCCorridasByIdUsuarioActualizacionIsInShouldWork() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where idUsuarioActualizacion in DEFAULT_ID_USUARIO_ACTUALIZACION or UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCCorridaShouldBeFound("idUsuarioActualizacion.in=" + DEFAULT_ID_USUARIO_ACTUALIZACION + "," + UPDATED_ID_USUARIO_ACTUALIZACION);

        // Get all the cCorridaList where idUsuarioActualizacion equals to UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCCorridaShouldNotBeFound("idUsuarioActualizacion.in=" + UPDATED_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCCorridasByIdUsuarioActualizacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where idUsuarioActualizacion is not null
        defaultCCorridaShouldBeFound("idUsuarioActualizacion.specified=true");

        // Get all the cCorridaList where idUsuarioActualizacion is null
        defaultCCorridaShouldNotBeFound("idUsuarioActualizacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCCorridasByIdUsuarioActualizacionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where idUsuarioActualizacion is greater than or equal to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCCorridaShouldBeFound("idUsuarioActualizacion.greaterThanOrEqual=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cCorridaList where idUsuarioActualizacion is greater than or equal to (DEFAULT_ID_USUARIO_ACTUALIZACION + 1)
        defaultCCorridaShouldNotBeFound("idUsuarioActualizacion.greaterThanOrEqual=" + (DEFAULT_ID_USUARIO_ACTUALIZACION + 1));
    }

    @Test
    @Transactional
    public void getAllCCorridasByIdUsuarioActualizacionIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where idUsuarioActualizacion is less than or equal to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCCorridaShouldBeFound("idUsuarioActualizacion.lessThanOrEqual=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cCorridaList where idUsuarioActualizacion is less than or equal to SMALLER_ID_USUARIO_ACTUALIZACION
        defaultCCorridaShouldNotBeFound("idUsuarioActualizacion.lessThanOrEqual=" + SMALLER_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCCorridasByIdUsuarioActualizacionIsLessThanSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where idUsuarioActualizacion is less than DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCCorridaShouldNotBeFound("idUsuarioActualizacion.lessThan=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cCorridaList where idUsuarioActualizacion is less than (DEFAULT_ID_USUARIO_ACTUALIZACION + 1)
        defaultCCorridaShouldBeFound("idUsuarioActualizacion.lessThan=" + (DEFAULT_ID_USUARIO_ACTUALIZACION + 1));
    }

    @Test
    @Transactional
    public void getAllCCorridasByIdUsuarioActualizacionIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where idUsuarioActualizacion is greater than DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCCorridaShouldNotBeFound("idUsuarioActualizacion.greaterThan=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cCorridaList where idUsuarioActualizacion is greater than SMALLER_ID_USUARIO_ACTUALIZACION
        defaultCCorridaShouldBeFound("idUsuarioActualizacion.greaterThan=" + SMALLER_ID_USUARIO_ACTUALIZACION);
    }


    @Test
    @Transactional
    public void getAllCCorridasByFechaActualizacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where fechaActualizacion equals to DEFAULT_FECHA_ACTUALIZACION
        defaultCCorridaShouldBeFound("fechaActualizacion.equals=" + DEFAULT_FECHA_ACTUALIZACION);

        // Get all the cCorridaList where fechaActualizacion equals to UPDATED_FECHA_ACTUALIZACION
        defaultCCorridaShouldNotBeFound("fechaActualizacion.equals=" + UPDATED_FECHA_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCCorridasByFechaActualizacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where fechaActualizacion not equals to DEFAULT_FECHA_ACTUALIZACION
        defaultCCorridaShouldNotBeFound("fechaActualizacion.notEquals=" + DEFAULT_FECHA_ACTUALIZACION);

        // Get all the cCorridaList where fechaActualizacion not equals to UPDATED_FECHA_ACTUALIZACION
        defaultCCorridaShouldBeFound("fechaActualizacion.notEquals=" + UPDATED_FECHA_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCCorridasByFechaActualizacionIsInShouldWork() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where fechaActualizacion in DEFAULT_FECHA_ACTUALIZACION or UPDATED_FECHA_ACTUALIZACION
        defaultCCorridaShouldBeFound("fechaActualizacion.in=" + DEFAULT_FECHA_ACTUALIZACION + "," + UPDATED_FECHA_ACTUALIZACION);

        // Get all the cCorridaList where fechaActualizacion equals to UPDATED_FECHA_ACTUALIZACION
        defaultCCorridaShouldNotBeFound("fechaActualizacion.in=" + UPDATED_FECHA_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCCorridasByFechaActualizacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where fechaActualizacion is not null
        defaultCCorridaShouldBeFound("fechaActualizacion.specified=true");

        // Get all the cCorridaList where fechaActualizacion is null
        defaultCCorridaShouldNotBeFound("fechaActualizacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCCorridasByNotasIsEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where notas equals to DEFAULT_NOTAS
        defaultCCorridaShouldBeFound("notas.equals=" + DEFAULT_NOTAS);

        // Get all the cCorridaList where notas equals to UPDATED_NOTAS
        defaultCCorridaShouldNotBeFound("notas.equals=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCCorridasByNotasIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where notas not equals to DEFAULT_NOTAS
        defaultCCorridaShouldNotBeFound("notas.notEquals=" + DEFAULT_NOTAS);

        // Get all the cCorridaList where notas not equals to UPDATED_NOTAS
        defaultCCorridaShouldBeFound("notas.notEquals=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCCorridasByNotasIsInShouldWork() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where notas in DEFAULT_NOTAS or UPDATED_NOTAS
        defaultCCorridaShouldBeFound("notas.in=" + DEFAULT_NOTAS + "," + UPDATED_NOTAS);

        // Get all the cCorridaList where notas equals to UPDATED_NOTAS
        defaultCCorridaShouldNotBeFound("notas.in=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCCorridasByNotasIsNullOrNotNull() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where notas is not null
        defaultCCorridaShouldBeFound("notas.specified=true");

        // Get all the cCorridaList where notas is null
        defaultCCorridaShouldNotBeFound("notas.specified=false");
    }
                @Test
    @Transactional
    public void getAllCCorridasByNotasContainsSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where notas contains DEFAULT_NOTAS
        defaultCCorridaShouldBeFound("notas.contains=" + DEFAULT_NOTAS);

        // Get all the cCorridaList where notas contains UPDATED_NOTAS
        defaultCCorridaShouldNotBeFound("notas.contains=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCCorridasByNotasNotContainsSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where notas does not contain DEFAULT_NOTAS
        defaultCCorridaShouldNotBeFound("notas.doesNotContain=" + DEFAULT_NOTAS);

        // Get all the cCorridaList where notas does not contain UPDATED_NOTAS
        defaultCCorridaShouldBeFound("notas.doesNotContain=" + UPDATED_NOTAS);
    }


    @Test
    @Transactional
    public void getAllCCorridasByEstatusIsEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where estatus equals to DEFAULT_ESTATUS
        defaultCCorridaShouldBeFound("estatus.equals=" + DEFAULT_ESTATUS);

        // Get all the cCorridaList where estatus equals to UPDATED_ESTATUS
        defaultCCorridaShouldNotBeFound("estatus.equals=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCCorridasByEstatusIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where estatus not equals to DEFAULT_ESTATUS
        defaultCCorridaShouldNotBeFound("estatus.notEquals=" + DEFAULT_ESTATUS);

        // Get all the cCorridaList where estatus not equals to UPDATED_ESTATUS
        defaultCCorridaShouldBeFound("estatus.notEquals=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCCorridasByEstatusIsInShouldWork() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where estatus in DEFAULT_ESTATUS or UPDATED_ESTATUS
        defaultCCorridaShouldBeFound("estatus.in=" + DEFAULT_ESTATUS + "," + UPDATED_ESTATUS);

        // Get all the cCorridaList where estatus equals to UPDATED_ESTATUS
        defaultCCorridaShouldNotBeFound("estatus.in=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCCorridasByEstatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where estatus is not null
        defaultCCorridaShouldBeFound("estatus.specified=true");

        // Get all the cCorridaList where estatus is null
        defaultCCorridaShouldNotBeFound("estatus.specified=false");
    }

    @Test
    @Transactional
    public void getAllCCorridasByEstatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where estatus is greater than or equal to DEFAULT_ESTATUS
        defaultCCorridaShouldBeFound("estatus.greaterThanOrEqual=" + DEFAULT_ESTATUS);

        // Get all the cCorridaList where estatus is greater than or equal to (DEFAULT_ESTATUS + 1)
        defaultCCorridaShouldNotBeFound("estatus.greaterThanOrEqual=" + (DEFAULT_ESTATUS + 1));
    }

    @Test
    @Transactional
    public void getAllCCorridasByEstatusIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where estatus is less than or equal to DEFAULT_ESTATUS
        defaultCCorridaShouldBeFound("estatus.lessThanOrEqual=" + DEFAULT_ESTATUS);

        // Get all the cCorridaList where estatus is less than or equal to SMALLER_ESTATUS
        defaultCCorridaShouldNotBeFound("estatus.lessThanOrEqual=" + SMALLER_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCCorridasByEstatusIsLessThanSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where estatus is less than DEFAULT_ESTATUS
        defaultCCorridaShouldNotBeFound("estatus.lessThan=" + DEFAULT_ESTATUS);

        // Get all the cCorridaList where estatus is less than (DEFAULT_ESTATUS + 1)
        defaultCCorridaShouldBeFound("estatus.lessThan=" + (DEFAULT_ESTATUS + 1));
    }

    @Test
    @Transactional
    public void getAllCCorridasByEstatusIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where estatus is greater than DEFAULT_ESTATUS
        defaultCCorridaShouldNotBeFound("estatus.greaterThan=" + DEFAULT_ESTATUS);

        // Get all the cCorridaList where estatus is greater than SMALLER_ESTATUS
        defaultCCorridaShouldBeFound("estatus.greaterThan=" + SMALLER_ESTATUS);
    }


    @Test
    @Transactional
    public void getAllCCorridasByBorradoIsEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where borrado equals to DEFAULT_BORRADO
        defaultCCorridaShouldBeFound("borrado.equals=" + DEFAULT_BORRADO);

        // Get all the cCorridaList where borrado equals to UPDATED_BORRADO
        defaultCCorridaShouldNotBeFound("borrado.equals=" + UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCCorridasByBorradoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where borrado not equals to DEFAULT_BORRADO
        defaultCCorridaShouldNotBeFound("borrado.notEquals=" + DEFAULT_BORRADO);

        // Get all the cCorridaList where borrado not equals to UPDATED_BORRADO
        defaultCCorridaShouldBeFound("borrado.notEquals=" + UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCCorridasByBorradoIsInShouldWork() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where borrado in DEFAULT_BORRADO or UPDATED_BORRADO
        defaultCCorridaShouldBeFound("borrado.in=" + DEFAULT_BORRADO + "," + UPDATED_BORRADO);

        // Get all the cCorridaList where borrado equals to UPDATED_BORRADO
        defaultCCorridaShouldNotBeFound("borrado.in=" + UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCCorridasByBorradoIsNullOrNotNull() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where borrado is not null
        defaultCCorridaShouldBeFound("borrado.specified=true");

        // Get all the cCorridaList where borrado is null
        defaultCCorridaShouldNotBeFound("borrado.specified=false");
    }

    @Test
    @Transactional
    public void getAllCCorridasByBorradoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where borrado is greater than or equal to DEFAULT_BORRADO
        defaultCCorridaShouldBeFound("borrado.greaterThanOrEqual=" + DEFAULT_BORRADO);

        // Get all the cCorridaList where borrado is greater than or equal to (DEFAULT_BORRADO + 1)
        defaultCCorridaShouldNotBeFound("borrado.greaterThanOrEqual=" + (DEFAULT_BORRADO + 1));
    }

    @Test
    @Transactional
    public void getAllCCorridasByBorradoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where borrado is less than or equal to DEFAULT_BORRADO
        defaultCCorridaShouldBeFound("borrado.lessThanOrEqual=" + DEFAULT_BORRADO);

        // Get all the cCorridaList where borrado is less than or equal to SMALLER_BORRADO
        defaultCCorridaShouldNotBeFound("borrado.lessThanOrEqual=" + SMALLER_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCCorridasByBorradoIsLessThanSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where borrado is less than DEFAULT_BORRADO
        defaultCCorridaShouldNotBeFound("borrado.lessThan=" + DEFAULT_BORRADO);

        // Get all the cCorridaList where borrado is less than (DEFAULT_BORRADO + 1)
        defaultCCorridaShouldBeFound("borrado.lessThan=" + (DEFAULT_BORRADO + 1));
    }

    @Test
    @Transactional
    public void getAllCCorridasByBorradoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        // Get all the cCorridaList where borrado is greater than DEFAULT_BORRADO
        defaultCCorridaShouldNotBeFound("borrado.greaterThan=" + DEFAULT_BORRADO);

        // Get all the cCorridaList where borrado is greater than SMALLER_BORRADO
        defaultCCorridaShouldBeFound("borrado.greaterThan=" + SMALLER_BORRADO);
    }


    @Test
    @Transactional
    public void getAllCCorridasByClienteIsEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);
        CCliente cliente = CClienteResourceIT.createEntity(em);
        em.persist(cliente);
        em.flush();
        cCorrida.setCliente(cliente);
        cCorridaRepository.saveAndFlush(cCorrida);
        Long clienteId = cliente.getId();

        // Get all the cCorridaList where cliente equals to clienteId
        defaultCCorridaShouldBeFound("clienteId.equals=" + clienteId);

        // Get all the cCorridaList where cliente equals to clienteId + 1
        defaultCCorridaShouldNotBeFound("clienteId.equals=" + (clienteId + 1));
    }


    @Test
    @Transactional
    public void getAllCCorridasByAutobusIsEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);
        CAutobus autobus = CAutobusResourceIT.createEntity(em);
        em.persist(autobus);
        em.flush();
        cCorrida.setAutobus(autobus);
        cCorridaRepository.saveAndFlush(cCorrida);
        Long autobusId = autobus.getId();

        // Get all the cCorridaList where autobus equals to autobusId
        defaultCCorridaShouldBeFound("autobusId.equals=" + autobusId);

        // Get all the cCorridaList where autobus equals to autobusId + 1
        defaultCCorridaShouldNotBeFound("autobusId.equals=" + (autobusId + 1));
    }


    @Test
    @Transactional
    public void getAllCCorridasByLugarSalidaIsEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);
        CLugarParada lugarSalida = CLugarParadaResourceIT.createEntity(em);
        em.persist(lugarSalida);
        em.flush();
        cCorrida.setLugarSalida(lugarSalida);
        cCorridaRepository.saveAndFlush(cCorrida);
        Long lugarSalidaId = lugarSalida.getId();

        // Get all the cCorridaList where lugarSalida equals to lugarSalidaId
        defaultCCorridaShouldBeFound("lugarSalidaId.equals=" + lugarSalidaId);

        // Get all the cCorridaList where lugarSalida equals to lugarSalidaId + 1
        defaultCCorridaShouldNotBeFound("lugarSalidaId.equals=" + (lugarSalidaId + 1));
    }


    @Test
    @Transactional
    public void getAllCCorridasByLugarLlegadaIsEqualToSomething() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);
        CLugarParada lugarLlegada = CLugarParadaResourceIT.createEntity(em);
        em.persist(lugarLlegada);
        em.flush();
        cCorrida.setLugarLlegada(lugarLlegada);
        cCorridaRepository.saveAndFlush(cCorrida);
        Long lugarLlegadaId = lugarLlegada.getId();

        // Get all the cCorridaList where lugarLlegada equals to lugarLlegadaId
        defaultCCorridaShouldBeFound("lugarLlegadaId.equals=" + lugarLlegadaId);

        // Get all the cCorridaList where lugarLlegada equals to lugarLlegadaId + 1
        defaultCCorridaShouldNotBeFound("lugarLlegadaId.equals=" + (lugarLlegadaId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCCorridaShouldBeFound(String filter) throws Exception {
        restCCorridaMockMvc.perform(get("/api/c-corridas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cCorrida.getId().intValue())))
            .andExpect(jsonPath("$.[*].claveCorrida").value(hasItem(DEFAULT_CLAVE_CORRIDA)))
            .andExpect(jsonPath("$.[*].horaSalida").value(hasItem(DEFAULT_HORA_SALIDA)))
            .andExpect(jsonPath("$.[*].horaLllegada").value(hasItem(DEFAULT_HORA_LLLEGADA)))
            .andExpect(jsonPath("$.[*].conexion").value(hasItem(DEFAULT_CONEXION.booleanValue())))
            .andExpect(jsonPath("$.[*].lugarConexion").value(hasItem(DEFAULT_LUGAR_CONEXION)))
            .andExpect(jsonPath("$.[*].diasSalida").value(hasItem(DEFAULT_DIAS_SALIDA)))
            .andExpect(jsonPath("$.[*].comentarios").value(hasItem(DEFAULT_COMENTARIOS)))
            .andExpect(jsonPath("$.[*].idUsuarioCreacion").value(hasItem(DEFAULT_ID_USUARIO_CREACION.intValue())))
            .andExpect(jsonPath("$.[*].fechaCreacion").value(hasItem(DEFAULT_FECHA_CREACION.toString())))
            .andExpect(jsonPath("$.[*].idUsuarioActualizacion").value(hasItem(DEFAULT_ID_USUARIO_ACTUALIZACION.intValue())))
            .andExpect(jsonPath("$.[*].fechaActualizacion").value(hasItem(DEFAULT_FECHA_ACTUALIZACION.toString())))
            .andExpect(jsonPath("$.[*].notas").value(hasItem(DEFAULT_NOTAS)))
            .andExpect(jsonPath("$.[*].estatus").value(hasItem(DEFAULT_ESTATUS)))
            .andExpect(jsonPath("$.[*].borrado").value(hasItem(DEFAULT_BORRADO)));

        // Check, that the count call also returns 1
        restCCorridaMockMvc.perform(get("/api/c-corridas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCCorridaShouldNotBeFound(String filter) throws Exception {
        restCCorridaMockMvc.perform(get("/api/c-corridas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCCorridaMockMvc.perform(get("/api/c-corridas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCCorrida() throws Exception {
        // Get the cCorrida
        restCCorridaMockMvc.perform(get("/api/c-corridas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCCorrida() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        int databaseSizeBeforeUpdate = cCorridaRepository.findAll().size();

        // Update the cCorrida
        CCorrida updatedCCorrida = cCorridaRepository.findById(cCorrida.getId()).get();
        // Disconnect from session so that the updates on updatedCCorrida are not directly saved in db
        em.detach(updatedCCorrida);
        updatedCCorrida
            .claveCorrida(UPDATED_CLAVE_CORRIDA)
            .horaSalida(UPDATED_HORA_SALIDA)
            .horaLllegada(UPDATED_HORA_LLLEGADA)
            .conexion(UPDATED_CONEXION)
            .lugarConexion(UPDATED_LUGAR_CONEXION)
            .diasSalida(UPDATED_DIAS_SALIDA)
            .comentarios(UPDATED_COMENTARIOS)
            .idUsuarioCreacion(UPDATED_ID_USUARIO_CREACION)
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .idUsuarioActualizacion(UPDATED_ID_USUARIO_ACTUALIZACION)
            .fechaActualizacion(UPDATED_FECHA_ACTUALIZACION)
            .notas(UPDATED_NOTAS)
            .estatus(UPDATED_ESTATUS)
            .borrado(UPDATED_BORRADO);
        CCorridaDTO cCorridaDTO = cCorridaMapper.toDto(updatedCCorrida);

        restCCorridaMockMvc.perform(put("/api/c-corridas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cCorridaDTO)))
            .andExpect(status().isOk());

        // Validate the CCorrida in the database
        List<CCorrida> cCorridaList = cCorridaRepository.findAll();
        assertThat(cCorridaList).hasSize(databaseSizeBeforeUpdate);
        CCorrida testCCorrida = cCorridaList.get(cCorridaList.size() - 1);
        assertThat(testCCorrida.getClaveCorrida()).isEqualTo(UPDATED_CLAVE_CORRIDA);
        assertThat(testCCorrida.getHoraSalida()).isEqualTo(UPDATED_HORA_SALIDA);
        assertThat(testCCorrida.getHoraLllegada()).isEqualTo(UPDATED_HORA_LLLEGADA);
        assertThat(testCCorrida.isConexion()).isEqualTo(UPDATED_CONEXION);
        assertThat(testCCorrida.getLugarConexion()).isEqualTo(UPDATED_LUGAR_CONEXION);
        assertThat(testCCorrida.getDiasSalida()).isEqualTo(UPDATED_DIAS_SALIDA);
        assertThat(testCCorrida.getComentarios()).isEqualTo(UPDATED_COMENTARIOS);
        assertThat(testCCorrida.getIdUsuarioCreacion()).isEqualTo(UPDATED_ID_USUARIO_CREACION);
        assertThat(testCCorrida.getFechaCreacion()).isEqualTo(UPDATED_FECHA_CREACION);
        assertThat(testCCorrida.getIdUsuarioActualizacion()).isEqualTo(UPDATED_ID_USUARIO_ACTUALIZACION);
        assertThat(testCCorrida.getFechaActualizacion()).isEqualTo(UPDATED_FECHA_ACTUALIZACION);
        assertThat(testCCorrida.getNotas()).isEqualTo(UPDATED_NOTAS);
        assertThat(testCCorrida.getEstatus()).isEqualTo(UPDATED_ESTATUS);
        assertThat(testCCorrida.getBorrado()).isEqualTo(UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void updateNonExistingCCorrida() throws Exception {
        int databaseSizeBeforeUpdate = cCorridaRepository.findAll().size();

        // Create the CCorrida
        CCorridaDTO cCorridaDTO = cCorridaMapper.toDto(cCorrida);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCCorridaMockMvc.perform(put("/api/c-corridas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cCorridaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CCorrida in the database
        List<CCorrida> cCorridaList = cCorridaRepository.findAll();
        assertThat(cCorridaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCCorrida() throws Exception {
        // Initialize the database
        cCorridaRepository.saveAndFlush(cCorrida);

        int databaseSizeBeforeDelete = cCorridaRepository.findAll().size();

        // Delete the cCorrida
        restCCorridaMockMvc.perform(delete("/api/c-corridas/{id}", cCorrida.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CCorrida> cCorridaList = cCorridaRepository.findAll();
        assertThat(cCorridaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
