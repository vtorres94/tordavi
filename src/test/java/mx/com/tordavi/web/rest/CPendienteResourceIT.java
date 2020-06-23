package mx.com.tordavi.web.rest;

import mx.com.tordavi.TordaviApp;
import mx.com.tordavi.domain.CPendiente;
import mx.com.tordavi.domain.CCliente;
import mx.com.tordavi.domain.CReservacion;
import mx.com.tordavi.repository.CPendienteRepository;
import mx.com.tordavi.service.CPendienteService;
import mx.com.tordavi.service.dto.CPendienteDTO;
import mx.com.tordavi.service.mapper.CPendienteMapper;
import mx.com.tordavi.service.dto.CPendienteCriteria;
import mx.com.tordavi.service.CPendienteQueryService;

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
 * Integration tests for the {@link CPendienteResource} REST controller.
 */
@SpringBootTest(classes = TordaviApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class CPendienteResourceIT {

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
    private CPendienteRepository cPendienteRepository;

    @Autowired
    private CPendienteMapper cPendienteMapper;

    @Autowired
    private CPendienteService cPendienteService;

    @Autowired
    private CPendienteQueryService cPendienteQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCPendienteMockMvc;

    private CPendiente cPendiente;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CPendiente createEntity(EntityManager em) {
        CPendiente cPendiente = new CPendiente()
            .comentarios(DEFAULT_COMENTARIOS)
            .idUsuarioCreacion(DEFAULT_ID_USUARIO_CREACION)
            .fechaCreacion(DEFAULT_FECHA_CREACION)
            .idUsuarioActualizacion(DEFAULT_ID_USUARIO_ACTUALIZACION)
            .fechaActualizacion(DEFAULT_FECHA_ACTUALIZACION)
            .notas(DEFAULT_NOTAS)
            .estatus(DEFAULT_ESTATUS)
            .borrado(DEFAULT_BORRADO);
        return cPendiente;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CPendiente createUpdatedEntity(EntityManager em) {
        CPendiente cPendiente = new CPendiente()
            .comentarios(UPDATED_COMENTARIOS)
            .idUsuarioCreacion(UPDATED_ID_USUARIO_CREACION)
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .idUsuarioActualizacion(UPDATED_ID_USUARIO_ACTUALIZACION)
            .fechaActualizacion(UPDATED_FECHA_ACTUALIZACION)
            .notas(UPDATED_NOTAS)
            .estatus(UPDATED_ESTATUS)
            .borrado(UPDATED_BORRADO);
        return cPendiente;
    }

    @BeforeEach
    public void initTest() {
        cPendiente = createEntity(em);
    }

    @Test
    @Transactional
    public void createCPendiente() throws Exception {
        int databaseSizeBeforeCreate = cPendienteRepository.findAll().size();

        // Create the CPendiente
        CPendienteDTO cPendienteDTO = cPendienteMapper.toDto(cPendiente);
        restCPendienteMockMvc.perform(post("/api/c-pendientes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cPendienteDTO)))
            .andExpect(status().isCreated());

        // Validate the CPendiente in the database
        List<CPendiente> cPendienteList = cPendienteRepository.findAll();
        assertThat(cPendienteList).hasSize(databaseSizeBeforeCreate + 1);
        CPendiente testCPendiente = cPendienteList.get(cPendienteList.size() - 1);
        assertThat(testCPendiente.getComentarios()).isEqualTo(DEFAULT_COMENTARIOS);
        assertThat(testCPendiente.getIdUsuarioCreacion()).isEqualTo(DEFAULT_ID_USUARIO_CREACION);
        assertThat(testCPendiente.getFechaCreacion()).isEqualTo(DEFAULT_FECHA_CREACION);
        assertThat(testCPendiente.getIdUsuarioActualizacion()).isEqualTo(DEFAULT_ID_USUARIO_ACTUALIZACION);
        assertThat(testCPendiente.getFechaActualizacion()).isEqualTo(DEFAULT_FECHA_ACTUALIZACION);
        assertThat(testCPendiente.getNotas()).isEqualTo(DEFAULT_NOTAS);
        assertThat(testCPendiente.getEstatus()).isEqualTo(DEFAULT_ESTATUS);
        assertThat(testCPendiente.getBorrado()).isEqualTo(DEFAULT_BORRADO);
    }

    @Test
    @Transactional
    public void createCPendienteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cPendienteRepository.findAll().size();

        // Create the CPendiente with an existing ID
        cPendiente.setId(1L);
        CPendienteDTO cPendienteDTO = cPendienteMapper.toDto(cPendiente);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCPendienteMockMvc.perform(post("/api/c-pendientes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cPendienteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CPendiente in the database
        List<CPendiente> cPendienteList = cPendienteRepository.findAll();
        assertThat(cPendienteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdUsuarioCreacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = cPendienteRepository.findAll().size();
        // set the field null
        cPendiente.setIdUsuarioCreacion(null);

        // Create the CPendiente, which fails.
        CPendienteDTO cPendienteDTO = cPendienteMapper.toDto(cPendiente);

        restCPendienteMockMvc.perform(post("/api/c-pendientes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cPendienteDTO)))
            .andExpect(status().isBadRequest());

        List<CPendiente> cPendienteList = cPendienteRepository.findAll();
        assertThat(cPendienteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFechaCreacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = cPendienteRepository.findAll().size();
        // set the field null
        cPendiente.setFechaCreacion(null);

        // Create the CPendiente, which fails.
        CPendienteDTO cPendienteDTO = cPendienteMapper.toDto(cPendiente);

        restCPendienteMockMvc.perform(post("/api/c-pendientes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cPendienteDTO)))
            .andExpect(status().isBadRequest());

        List<CPendiente> cPendienteList = cPendienteRepository.findAll();
        assertThat(cPendienteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = cPendienteRepository.findAll().size();
        // set the field null
        cPendiente.setEstatus(null);

        // Create the CPendiente, which fails.
        CPendienteDTO cPendienteDTO = cPendienteMapper.toDto(cPendiente);

        restCPendienteMockMvc.perform(post("/api/c-pendientes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cPendienteDTO)))
            .andExpect(status().isBadRequest());

        List<CPendiente> cPendienteList = cPendienteRepository.findAll();
        assertThat(cPendienteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBorradoIsRequired() throws Exception {
        int databaseSizeBeforeTest = cPendienteRepository.findAll().size();
        // set the field null
        cPendiente.setBorrado(null);

        // Create the CPendiente, which fails.
        CPendienteDTO cPendienteDTO = cPendienteMapper.toDto(cPendiente);

        restCPendienteMockMvc.perform(post("/api/c-pendientes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cPendienteDTO)))
            .andExpect(status().isBadRequest());

        List<CPendiente> cPendienteList = cPendienteRepository.findAll();
        assertThat(cPendienteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCPendientes() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList
        restCPendienteMockMvc.perform(get("/api/c-pendientes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cPendiente.getId().intValue())))
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
    public void getCPendiente() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get the cPendiente
        restCPendienteMockMvc.perform(get("/api/c-pendientes/{id}", cPendiente.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cPendiente.getId().intValue()))
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
    public void getCPendientesByIdFiltering() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        Long id = cPendiente.getId();

        defaultCPendienteShouldBeFound("id.equals=" + id);
        defaultCPendienteShouldNotBeFound("id.notEquals=" + id);

        defaultCPendienteShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCPendienteShouldNotBeFound("id.greaterThan=" + id);

        defaultCPendienteShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCPendienteShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllCPendientesByComentariosIsEqualToSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where comentarios equals to DEFAULT_COMENTARIOS
        defaultCPendienteShouldBeFound("comentarios.equals=" + DEFAULT_COMENTARIOS);

        // Get all the cPendienteList where comentarios equals to UPDATED_COMENTARIOS
        defaultCPendienteShouldNotBeFound("comentarios.equals=" + UPDATED_COMENTARIOS);
    }

    @Test
    @Transactional
    public void getAllCPendientesByComentariosIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where comentarios not equals to DEFAULT_COMENTARIOS
        defaultCPendienteShouldNotBeFound("comentarios.notEquals=" + DEFAULT_COMENTARIOS);

        // Get all the cPendienteList where comentarios not equals to UPDATED_COMENTARIOS
        defaultCPendienteShouldBeFound("comentarios.notEquals=" + UPDATED_COMENTARIOS);
    }

    @Test
    @Transactional
    public void getAllCPendientesByComentariosIsInShouldWork() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where comentarios in DEFAULT_COMENTARIOS or UPDATED_COMENTARIOS
        defaultCPendienteShouldBeFound("comentarios.in=" + DEFAULT_COMENTARIOS + "," + UPDATED_COMENTARIOS);

        // Get all the cPendienteList where comentarios equals to UPDATED_COMENTARIOS
        defaultCPendienteShouldNotBeFound("comentarios.in=" + UPDATED_COMENTARIOS);
    }

    @Test
    @Transactional
    public void getAllCPendientesByComentariosIsNullOrNotNull() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where comentarios is not null
        defaultCPendienteShouldBeFound("comentarios.specified=true");

        // Get all the cPendienteList where comentarios is null
        defaultCPendienteShouldNotBeFound("comentarios.specified=false");
    }
                @Test
    @Transactional
    public void getAllCPendientesByComentariosContainsSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where comentarios contains DEFAULT_COMENTARIOS
        defaultCPendienteShouldBeFound("comentarios.contains=" + DEFAULT_COMENTARIOS);

        // Get all the cPendienteList where comentarios contains UPDATED_COMENTARIOS
        defaultCPendienteShouldNotBeFound("comentarios.contains=" + UPDATED_COMENTARIOS);
    }

    @Test
    @Transactional
    public void getAllCPendientesByComentariosNotContainsSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where comentarios does not contain DEFAULT_COMENTARIOS
        defaultCPendienteShouldNotBeFound("comentarios.doesNotContain=" + DEFAULT_COMENTARIOS);

        // Get all the cPendienteList where comentarios does not contain UPDATED_COMENTARIOS
        defaultCPendienteShouldBeFound("comentarios.doesNotContain=" + UPDATED_COMENTARIOS);
    }


    @Test
    @Transactional
    public void getAllCPendientesByIdUsuarioCreacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where idUsuarioCreacion equals to DEFAULT_ID_USUARIO_CREACION
        defaultCPendienteShouldBeFound("idUsuarioCreacion.equals=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cPendienteList where idUsuarioCreacion equals to UPDATED_ID_USUARIO_CREACION
        defaultCPendienteShouldNotBeFound("idUsuarioCreacion.equals=" + UPDATED_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCPendientesByIdUsuarioCreacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where idUsuarioCreacion not equals to DEFAULT_ID_USUARIO_CREACION
        defaultCPendienteShouldNotBeFound("idUsuarioCreacion.notEquals=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cPendienteList where idUsuarioCreacion not equals to UPDATED_ID_USUARIO_CREACION
        defaultCPendienteShouldBeFound("idUsuarioCreacion.notEquals=" + UPDATED_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCPendientesByIdUsuarioCreacionIsInShouldWork() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where idUsuarioCreacion in DEFAULT_ID_USUARIO_CREACION or UPDATED_ID_USUARIO_CREACION
        defaultCPendienteShouldBeFound("idUsuarioCreacion.in=" + DEFAULT_ID_USUARIO_CREACION + "," + UPDATED_ID_USUARIO_CREACION);

        // Get all the cPendienteList where idUsuarioCreacion equals to UPDATED_ID_USUARIO_CREACION
        defaultCPendienteShouldNotBeFound("idUsuarioCreacion.in=" + UPDATED_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCPendientesByIdUsuarioCreacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where idUsuarioCreacion is not null
        defaultCPendienteShouldBeFound("idUsuarioCreacion.specified=true");

        // Get all the cPendienteList where idUsuarioCreacion is null
        defaultCPendienteShouldNotBeFound("idUsuarioCreacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCPendientesByIdUsuarioCreacionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where idUsuarioCreacion is greater than or equal to DEFAULT_ID_USUARIO_CREACION
        defaultCPendienteShouldBeFound("idUsuarioCreacion.greaterThanOrEqual=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cPendienteList where idUsuarioCreacion is greater than or equal to (DEFAULT_ID_USUARIO_CREACION + 1)
        defaultCPendienteShouldNotBeFound("idUsuarioCreacion.greaterThanOrEqual=" + (DEFAULT_ID_USUARIO_CREACION + 1));
    }

    @Test
    @Transactional
    public void getAllCPendientesByIdUsuarioCreacionIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where idUsuarioCreacion is less than or equal to DEFAULT_ID_USUARIO_CREACION
        defaultCPendienteShouldBeFound("idUsuarioCreacion.lessThanOrEqual=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cPendienteList where idUsuarioCreacion is less than or equal to SMALLER_ID_USUARIO_CREACION
        defaultCPendienteShouldNotBeFound("idUsuarioCreacion.lessThanOrEqual=" + SMALLER_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCPendientesByIdUsuarioCreacionIsLessThanSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where idUsuarioCreacion is less than DEFAULT_ID_USUARIO_CREACION
        defaultCPendienteShouldNotBeFound("idUsuarioCreacion.lessThan=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cPendienteList where idUsuarioCreacion is less than (DEFAULT_ID_USUARIO_CREACION + 1)
        defaultCPendienteShouldBeFound("idUsuarioCreacion.lessThan=" + (DEFAULT_ID_USUARIO_CREACION + 1));
    }

    @Test
    @Transactional
    public void getAllCPendientesByIdUsuarioCreacionIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where idUsuarioCreacion is greater than DEFAULT_ID_USUARIO_CREACION
        defaultCPendienteShouldNotBeFound("idUsuarioCreacion.greaterThan=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cPendienteList where idUsuarioCreacion is greater than SMALLER_ID_USUARIO_CREACION
        defaultCPendienteShouldBeFound("idUsuarioCreacion.greaterThan=" + SMALLER_ID_USUARIO_CREACION);
    }


    @Test
    @Transactional
    public void getAllCPendientesByFechaCreacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where fechaCreacion equals to DEFAULT_FECHA_CREACION
        defaultCPendienteShouldBeFound("fechaCreacion.equals=" + DEFAULT_FECHA_CREACION);

        // Get all the cPendienteList where fechaCreacion equals to UPDATED_FECHA_CREACION
        defaultCPendienteShouldNotBeFound("fechaCreacion.equals=" + UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void getAllCPendientesByFechaCreacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where fechaCreacion not equals to DEFAULT_FECHA_CREACION
        defaultCPendienteShouldNotBeFound("fechaCreacion.notEquals=" + DEFAULT_FECHA_CREACION);

        // Get all the cPendienteList where fechaCreacion not equals to UPDATED_FECHA_CREACION
        defaultCPendienteShouldBeFound("fechaCreacion.notEquals=" + UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void getAllCPendientesByFechaCreacionIsInShouldWork() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where fechaCreacion in DEFAULT_FECHA_CREACION or UPDATED_FECHA_CREACION
        defaultCPendienteShouldBeFound("fechaCreacion.in=" + DEFAULT_FECHA_CREACION + "," + UPDATED_FECHA_CREACION);

        // Get all the cPendienteList where fechaCreacion equals to UPDATED_FECHA_CREACION
        defaultCPendienteShouldNotBeFound("fechaCreacion.in=" + UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void getAllCPendientesByFechaCreacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where fechaCreacion is not null
        defaultCPendienteShouldBeFound("fechaCreacion.specified=true");

        // Get all the cPendienteList where fechaCreacion is null
        defaultCPendienteShouldNotBeFound("fechaCreacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCPendientesByIdUsuarioActualizacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where idUsuarioActualizacion equals to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCPendienteShouldBeFound("idUsuarioActualizacion.equals=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cPendienteList where idUsuarioActualizacion equals to UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCPendienteShouldNotBeFound("idUsuarioActualizacion.equals=" + UPDATED_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCPendientesByIdUsuarioActualizacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where idUsuarioActualizacion not equals to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCPendienteShouldNotBeFound("idUsuarioActualizacion.notEquals=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cPendienteList where idUsuarioActualizacion not equals to UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCPendienteShouldBeFound("idUsuarioActualizacion.notEquals=" + UPDATED_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCPendientesByIdUsuarioActualizacionIsInShouldWork() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where idUsuarioActualizacion in DEFAULT_ID_USUARIO_ACTUALIZACION or UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCPendienteShouldBeFound("idUsuarioActualizacion.in=" + DEFAULT_ID_USUARIO_ACTUALIZACION + "," + UPDATED_ID_USUARIO_ACTUALIZACION);

        // Get all the cPendienteList where idUsuarioActualizacion equals to UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCPendienteShouldNotBeFound("idUsuarioActualizacion.in=" + UPDATED_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCPendientesByIdUsuarioActualizacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where idUsuarioActualizacion is not null
        defaultCPendienteShouldBeFound("idUsuarioActualizacion.specified=true");

        // Get all the cPendienteList where idUsuarioActualizacion is null
        defaultCPendienteShouldNotBeFound("idUsuarioActualizacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCPendientesByIdUsuarioActualizacionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where idUsuarioActualizacion is greater than or equal to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCPendienteShouldBeFound("idUsuarioActualizacion.greaterThanOrEqual=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cPendienteList where idUsuarioActualizacion is greater than or equal to (DEFAULT_ID_USUARIO_ACTUALIZACION + 1)
        defaultCPendienteShouldNotBeFound("idUsuarioActualizacion.greaterThanOrEqual=" + (DEFAULT_ID_USUARIO_ACTUALIZACION + 1));
    }

    @Test
    @Transactional
    public void getAllCPendientesByIdUsuarioActualizacionIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where idUsuarioActualizacion is less than or equal to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCPendienteShouldBeFound("idUsuarioActualizacion.lessThanOrEqual=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cPendienteList where idUsuarioActualizacion is less than or equal to SMALLER_ID_USUARIO_ACTUALIZACION
        defaultCPendienteShouldNotBeFound("idUsuarioActualizacion.lessThanOrEqual=" + SMALLER_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCPendientesByIdUsuarioActualizacionIsLessThanSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where idUsuarioActualizacion is less than DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCPendienteShouldNotBeFound("idUsuarioActualizacion.lessThan=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cPendienteList where idUsuarioActualizacion is less than (DEFAULT_ID_USUARIO_ACTUALIZACION + 1)
        defaultCPendienteShouldBeFound("idUsuarioActualizacion.lessThan=" + (DEFAULT_ID_USUARIO_ACTUALIZACION + 1));
    }

    @Test
    @Transactional
    public void getAllCPendientesByIdUsuarioActualizacionIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where idUsuarioActualizacion is greater than DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCPendienteShouldNotBeFound("idUsuarioActualizacion.greaterThan=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cPendienteList where idUsuarioActualizacion is greater than SMALLER_ID_USUARIO_ACTUALIZACION
        defaultCPendienteShouldBeFound("idUsuarioActualizacion.greaterThan=" + SMALLER_ID_USUARIO_ACTUALIZACION);
    }


    @Test
    @Transactional
    public void getAllCPendientesByFechaActualizacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where fechaActualizacion equals to DEFAULT_FECHA_ACTUALIZACION
        defaultCPendienteShouldBeFound("fechaActualizacion.equals=" + DEFAULT_FECHA_ACTUALIZACION);

        // Get all the cPendienteList where fechaActualizacion equals to UPDATED_FECHA_ACTUALIZACION
        defaultCPendienteShouldNotBeFound("fechaActualizacion.equals=" + UPDATED_FECHA_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCPendientesByFechaActualizacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where fechaActualizacion not equals to DEFAULT_FECHA_ACTUALIZACION
        defaultCPendienteShouldNotBeFound("fechaActualizacion.notEquals=" + DEFAULT_FECHA_ACTUALIZACION);

        // Get all the cPendienteList where fechaActualizacion not equals to UPDATED_FECHA_ACTUALIZACION
        defaultCPendienteShouldBeFound("fechaActualizacion.notEquals=" + UPDATED_FECHA_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCPendientesByFechaActualizacionIsInShouldWork() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where fechaActualizacion in DEFAULT_FECHA_ACTUALIZACION or UPDATED_FECHA_ACTUALIZACION
        defaultCPendienteShouldBeFound("fechaActualizacion.in=" + DEFAULT_FECHA_ACTUALIZACION + "," + UPDATED_FECHA_ACTUALIZACION);

        // Get all the cPendienteList where fechaActualizacion equals to UPDATED_FECHA_ACTUALIZACION
        defaultCPendienteShouldNotBeFound("fechaActualizacion.in=" + UPDATED_FECHA_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCPendientesByFechaActualizacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where fechaActualizacion is not null
        defaultCPendienteShouldBeFound("fechaActualizacion.specified=true");

        // Get all the cPendienteList where fechaActualizacion is null
        defaultCPendienteShouldNotBeFound("fechaActualizacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCPendientesByNotasIsEqualToSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where notas equals to DEFAULT_NOTAS
        defaultCPendienteShouldBeFound("notas.equals=" + DEFAULT_NOTAS);

        // Get all the cPendienteList where notas equals to UPDATED_NOTAS
        defaultCPendienteShouldNotBeFound("notas.equals=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCPendientesByNotasIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where notas not equals to DEFAULT_NOTAS
        defaultCPendienteShouldNotBeFound("notas.notEquals=" + DEFAULT_NOTAS);

        // Get all the cPendienteList where notas not equals to UPDATED_NOTAS
        defaultCPendienteShouldBeFound("notas.notEquals=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCPendientesByNotasIsInShouldWork() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where notas in DEFAULT_NOTAS or UPDATED_NOTAS
        defaultCPendienteShouldBeFound("notas.in=" + DEFAULT_NOTAS + "," + UPDATED_NOTAS);

        // Get all the cPendienteList where notas equals to UPDATED_NOTAS
        defaultCPendienteShouldNotBeFound("notas.in=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCPendientesByNotasIsNullOrNotNull() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where notas is not null
        defaultCPendienteShouldBeFound("notas.specified=true");

        // Get all the cPendienteList where notas is null
        defaultCPendienteShouldNotBeFound("notas.specified=false");
    }
                @Test
    @Transactional
    public void getAllCPendientesByNotasContainsSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where notas contains DEFAULT_NOTAS
        defaultCPendienteShouldBeFound("notas.contains=" + DEFAULT_NOTAS);

        // Get all the cPendienteList where notas contains UPDATED_NOTAS
        defaultCPendienteShouldNotBeFound("notas.contains=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCPendientesByNotasNotContainsSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where notas does not contain DEFAULT_NOTAS
        defaultCPendienteShouldNotBeFound("notas.doesNotContain=" + DEFAULT_NOTAS);

        // Get all the cPendienteList where notas does not contain UPDATED_NOTAS
        defaultCPendienteShouldBeFound("notas.doesNotContain=" + UPDATED_NOTAS);
    }


    @Test
    @Transactional
    public void getAllCPendientesByEstatusIsEqualToSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where estatus equals to DEFAULT_ESTATUS
        defaultCPendienteShouldBeFound("estatus.equals=" + DEFAULT_ESTATUS);

        // Get all the cPendienteList where estatus equals to UPDATED_ESTATUS
        defaultCPendienteShouldNotBeFound("estatus.equals=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCPendientesByEstatusIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where estatus not equals to DEFAULT_ESTATUS
        defaultCPendienteShouldNotBeFound("estatus.notEquals=" + DEFAULT_ESTATUS);

        // Get all the cPendienteList where estatus not equals to UPDATED_ESTATUS
        defaultCPendienteShouldBeFound("estatus.notEquals=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCPendientesByEstatusIsInShouldWork() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where estatus in DEFAULT_ESTATUS or UPDATED_ESTATUS
        defaultCPendienteShouldBeFound("estatus.in=" + DEFAULT_ESTATUS + "," + UPDATED_ESTATUS);

        // Get all the cPendienteList where estatus equals to UPDATED_ESTATUS
        defaultCPendienteShouldNotBeFound("estatus.in=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCPendientesByEstatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where estatus is not null
        defaultCPendienteShouldBeFound("estatus.specified=true");

        // Get all the cPendienteList where estatus is null
        defaultCPendienteShouldNotBeFound("estatus.specified=false");
    }

    @Test
    @Transactional
    public void getAllCPendientesByEstatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where estatus is greater than or equal to DEFAULT_ESTATUS
        defaultCPendienteShouldBeFound("estatus.greaterThanOrEqual=" + DEFAULT_ESTATUS);

        // Get all the cPendienteList where estatus is greater than or equal to (DEFAULT_ESTATUS + 1)
        defaultCPendienteShouldNotBeFound("estatus.greaterThanOrEqual=" + (DEFAULT_ESTATUS + 1));
    }

    @Test
    @Transactional
    public void getAllCPendientesByEstatusIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where estatus is less than or equal to DEFAULT_ESTATUS
        defaultCPendienteShouldBeFound("estatus.lessThanOrEqual=" + DEFAULT_ESTATUS);

        // Get all the cPendienteList where estatus is less than or equal to SMALLER_ESTATUS
        defaultCPendienteShouldNotBeFound("estatus.lessThanOrEqual=" + SMALLER_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCPendientesByEstatusIsLessThanSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where estatus is less than DEFAULT_ESTATUS
        defaultCPendienteShouldNotBeFound("estatus.lessThan=" + DEFAULT_ESTATUS);

        // Get all the cPendienteList where estatus is less than (DEFAULT_ESTATUS + 1)
        defaultCPendienteShouldBeFound("estatus.lessThan=" + (DEFAULT_ESTATUS + 1));
    }

    @Test
    @Transactional
    public void getAllCPendientesByEstatusIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where estatus is greater than DEFAULT_ESTATUS
        defaultCPendienteShouldNotBeFound("estatus.greaterThan=" + DEFAULT_ESTATUS);

        // Get all the cPendienteList where estatus is greater than SMALLER_ESTATUS
        defaultCPendienteShouldBeFound("estatus.greaterThan=" + SMALLER_ESTATUS);
    }


    @Test
    @Transactional
    public void getAllCPendientesByBorradoIsEqualToSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where borrado equals to DEFAULT_BORRADO
        defaultCPendienteShouldBeFound("borrado.equals=" + DEFAULT_BORRADO);

        // Get all the cPendienteList where borrado equals to UPDATED_BORRADO
        defaultCPendienteShouldNotBeFound("borrado.equals=" + UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCPendientesByBorradoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where borrado not equals to DEFAULT_BORRADO
        defaultCPendienteShouldNotBeFound("borrado.notEquals=" + DEFAULT_BORRADO);

        // Get all the cPendienteList where borrado not equals to UPDATED_BORRADO
        defaultCPendienteShouldBeFound("borrado.notEquals=" + UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCPendientesByBorradoIsInShouldWork() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where borrado in DEFAULT_BORRADO or UPDATED_BORRADO
        defaultCPendienteShouldBeFound("borrado.in=" + DEFAULT_BORRADO + "," + UPDATED_BORRADO);

        // Get all the cPendienteList where borrado equals to UPDATED_BORRADO
        defaultCPendienteShouldNotBeFound("borrado.in=" + UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCPendientesByBorradoIsNullOrNotNull() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where borrado is not null
        defaultCPendienteShouldBeFound("borrado.specified=true");

        // Get all the cPendienteList where borrado is null
        defaultCPendienteShouldNotBeFound("borrado.specified=false");
    }

    @Test
    @Transactional
    public void getAllCPendientesByBorradoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where borrado is greater than or equal to DEFAULT_BORRADO
        defaultCPendienteShouldBeFound("borrado.greaterThanOrEqual=" + DEFAULT_BORRADO);

        // Get all the cPendienteList where borrado is greater than or equal to (DEFAULT_BORRADO + 1)
        defaultCPendienteShouldNotBeFound("borrado.greaterThanOrEqual=" + (DEFAULT_BORRADO + 1));
    }

    @Test
    @Transactional
    public void getAllCPendientesByBorradoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where borrado is less than or equal to DEFAULT_BORRADO
        defaultCPendienteShouldBeFound("borrado.lessThanOrEqual=" + DEFAULT_BORRADO);

        // Get all the cPendienteList where borrado is less than or equal to SMALLER_BORRADO
        defaultCPendienteShouldNotBeFound("borrado.lessThanOrEqual=" + SMALLER_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCPendientesByBorradoIsLessThanSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where borrado is less than DEFAULT_BORRADO
        defaultCPendienteShouldNotBeFound("borrado.lessThan=" + DEFAULT_BORRADO);

        // Get all the cPendienteList where borrado is less than (DEFAULT_BORRADO + 1)
        defaultCPendienteShouldBeFound("borrado.lessThan=" + (DEFAULT_BORRADO + 1));
    }

    @Test
    @Transactional
    public void getAllCPendientesByBorradoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        // Get all the cPendienteList where borrado is greater than DEFAULT_BORRADO
        defaultCPendienteShouldNotBeFound("borrado.greaterThan=" + DEFAULT_BORRADO);

        // Get all the cPendienteList where borrado is greater than SMALLER_BORRADO
        defaultCPendienteShouldBeFound("borrado.greaterThan=" + SMALLER_BORRADO);
    }


    @Test
    @Transactional
    public void getAllCPendientesByClienteIsEqualToSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);
        CCliente cliente = CClienteResourceIT.createEntity(em);
        em.persist(cliente);
        em.flush();
        cPendiente.setCliente(cliente);
        cPendienteRepository.saveAndFlush(cPendiente);
        Long clienteId = cliente.getId();

        // Get all the cPendienteList where cliente equals to clienteId
        defaultCPendienteShouldBeFound("clienteId.equals=" + clienteId);

        // Get all the cPendienteList where cliente equals to clienteId + 1
        defaultCPendienteShouldNotBeFound("clienteId.equals=" + (clienteId + 1));
    }


    @Test
    @Transactional
    public void getAllCPendientesByReservacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);
        CReservacion reservacion = CReservacionResourceIT.createEntity(em);
        em.persist(reservacion);
        em.flush();
        cPendiente.setReservacion(reservacion);
        cPendienteRepository.saveAndFlush(cPendiente);
        Long reservacionId = reservacion.getId();

        // Get all the cPendienteList where reservacion equals to reservacionId
        defaultCPendienteShouldBeFound("reservacionId.equals=" + reservacionId);

        // Get all the cPendienteList where reservacion equals to reservacionId + 1
        defaultCPendienteShouldNotBeFound("reservacionId.equals=" + (reservacionId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCPendienteShouldBeFound(String filter) throws Exception {
        restCPendienteMockMvc.perform(get("/api/c-pendientes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cPendiente.getId().intValue())))
            .andExpect(jsonPath("$.[*].comentarios").value(hasItem(DEFAULT_COMENTARIOS)))
            .andExpect(jsonPath("$.[*].idUsuarioCreacion").value(hasItem(DEFAULT_ID_USUARIO_CREACION.intValue())))
            .andExpect(jsonPath("$.[*].fechaCreacion").value(hasItem(DEFAULT_FECHA_CREACION.toString())))
            .andExpect(jsonPath("$.[*].idUsuarioActualizacion").value(hasItem(DEFAULT_ID_USUARIO_ACTUALIZACION.intValue())))
            .andExpect(jsonPath("$.[*].fechaActualizacion").value(hasItem(DEFAULT_FECHA_ACTUALIZACION.toString())))
            .andExpect(jsonPath("$.[*].notas").value(hasItem(DEFAULT_NOTAS)))
            .andExpect(jsonPath("$.[*].estatus").value(hasItem(DEFAULT_ESTATUS)))
            .andExpect(jsonPath("$.[*].borrado").value(hasItem(DEFAULT_BORRADO)));

        // Check, that the count call also returns 1
        restCPendienteMockMvc.perform(get("/api/c-pendientes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCPendienteShouldNotBeFound(String filter) throws Exception {
        restCPendienteMockMvc.perform(get("/api/c-pendientes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCPendienteMockMvc.perform(get("/api/c-pendientes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCPendiente() throws Exception {
        // Get the cPendiente
        restCPendienteMockMvc.perform(get("/api/c-pendientes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCPendiente() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        int databaseSizeBeforeUpdate = cPendienteRepository.findAll().size();

        // Update the cPendiente
        CPendiente updatedCPendiente = cPendienteRepository.findById(cPendiente.getId()).get();
        // Disconnect from session so that the updates on updatedCPendiente are not directly saved in db
        em.detach(updatedCPendiente);
        updatedCPendiente
            .comentarios(UPDATED_COMENTARIOS)
            .idUsuarioCreacion(UPDATED_ID_USUARIO_CREACION)
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .idUsuarioActualizacion(UPDATED_ID_USUARIO_ACTUALIZACION)
            .fechaActualizacion(UPDATED_FECHA_ACTUALIZACION)
            .notas(UPDATED_NOTAS)
            .estatus(UPDATED_ESTATUS)
            .borrado(UPDATED_BORRADO);
        CPendienteDTO cPendienteDTO = cPendienteMapper.toDto(updatedCPendiente);

        restCPendienteMockMvc.perform(put("/api/c-pendientes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cPendienteDTO)))
            .andExpect(status().isOk());

        // Validate the CPendiente in the database
        List<CPendiente> cPendienteList = cPendienteRepository.findAll();
        assertThat(cPendienteList).hasSize(databaseSizeBeforeUpdate);
        CPendiente testCPendiente = cPendienteList.get(cPendienteList.size() - 1);
        assertThat(testCPendiente.getComentarios()).isEqualTo(UPDATED_COMENTARIOS);
        assertThat(testCPendiente.getIdUsuarioCreacion()).isEqualTo(UPDATED_ID_USUARIO_CREACION);
        assertThat(testCPendiente.getFechaCreacion()).isEqualTo(UPDATED_FECHA_CREACION);
        assertThat(testCPendiente.getIdUsuarioActualizacion()).isEqualTo(UPDATED_ID_USUARIO_ACTUALIZACION);
        assertThat(testCPendiente.getFechaActualizacion()).isEqualTo(UPDATED_FECHA_ACTUALIZACION);
        assertThat(testCPendiente.getNotas()).isEqualTo(UPDATED_NOTAS);
        assertThat(testCPendiente.getEstatus()).isEqualTo(UPDATED_ESTATUS);
        assertThat(testCPendiente.getBorrado()).isEqualTo(UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void updateNonExistingCPendiente() throws Exception {
        int databaseSizeBeforeUpdate = cPendienteRepository.findAll().size();

        // Create the CPendiente
        CPendienteDTO cPendienteDTO = cPendienteMapper.toDto(cPendiente);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCPendienteMockMvc.perform(put("/api/c-pendientes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cPendienteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CPendiente in the database
        List<CPendiente> cPendienteList = cPendienteRepository.findAll();
        assertThat(cPendienteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCPendiente() throws Exception {
        // Initialize the database
        cPendienteRepository.saveAndFlush(cPendiente);

        int databaseSizeBeforeDelete = cPendienteRepository.findAll().size();

        // Delete the cPendiente
        restCPendienteMockMvc.perform(delete("/api/c-pendientes/{id}", cPendiente.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CPendiente> cPendienteList = cPendienteRepository.findAll();
        assertThat(cPendienteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
