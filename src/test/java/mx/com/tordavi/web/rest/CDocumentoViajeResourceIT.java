package mx.com.tordavi.web.rest;

import mx.com.tordavi.TordaviApp;
import mx.com.tordavi.domain.CDocumentoViaje;
import mx.com.tordavi.domain.CCliente;
import mx.com.tordavi.domain.CPasajero;
import mx.com.tordavi.repository.CDocumentoViajeRepository;
import mx.com.tordavi.service.CDocumentoViajeService;
import mx.com.tordavi.service.dto.CDocumentoViajeDTO;
import mx.com.tordavi.service.mapper.CDocumentoViajeMapper;
import mx.com.tordavi.service.dto.CDocumentoViajeCriteria;
import mx.com.tordavi.service.CDocumentoViajeQueryService;

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
 * Integration tests for the {@link CDocumentoViajeResource} REST controller.
 */
@SpringBootTest(classes = TordaviApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class CDocumentoViajeResourceIT {

    private static final String DEFAULT_CLAVE_DOCUMENTO = "AAAAAAAAAA";
    private static final String UPDATED_CLAVE_DOCUMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_DOCUMENTO = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENTO = "BBBBBBBBBB";

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
    private CDocumentoViajeRepository cDocumentoViajeRepository;

    @Autowired
    private CDocumentoViajeMapper cDocumentoViajeMapper;

    @Autowired
    private CDocumentoViajeService cDocumentoViajeService;

    @Autowired
    private CDocumentoViajeQueryService cDocumentoViajeQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCDocumentoViajeMockMvc;

    private CDocumentoViaje cDocumentoViaje;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CDocumentoViaje createEntity(EntityManager em) {
        CDocumentoViaje cDocumentoViaje = new CDocumentoViaje()
            .claveDocumento(DEFAULT_CLAVE_DOCUMENTO)
            .documento(DEFAULT_DOCUMENTO)
            .idUsuarioCreacion(DEFAULT_ID_USUARIO_CREACION)
            .fechaCreacion(DEFAULT_FECHA_CREACION)
            .idUsuarioActualizacion(DEFAULT_ID_USUARIO_ACTUALIZACION)
            .fechaActualizacion(DEFAULT_FECHA_ACTUALIZACION)
            .notas(DEFAULT_NOTAS)
            .estatus(DEFAULT_ESTATUS)
            .borrado(DEFAULT_BORRADO);
        return cDocumentoViaje;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CDocumentoViaje createUpdatedEntity(EntityManager em) {
        CDocumentoViaje cDocumentoViaje = new CDocumentoViaje()
            .claveDocumento(UPDATED_CLAVE_DOCUMENTO)
            .documento(UPDATED_DOCUMENTO)
            .idUsuarioCreacion(UPDATED_ID_USUARIO_CREACION)
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .idUsuarioActualizacion(UPDATED_ID_USUARIO_ACTUALIZACION)
            .fechaActualizacion(UPDATED_FECHA_ACTUALIZACION)
            .notas(UPDATED_NOTAS)
            .estatus(UPDATED_ESTATUS)
            .borrado(UPDATED_BORRADO);
        return cDocumentoViaje;
    }

    @BeforeEach
    public void initTest() {
        cDocumentoViaje = createEntity(em);
    }

    @Test
    @Transactional
    public void createCDocumentoViaje() throws Exception {
        int databaseSizeBeforeCreate = cDocumentoViajeRepository.findAll().size();

        // Create the CDocumentoViaje
        CDocumentoViajeDTO cDocumentoViajeDTO = cDocumentoViajeMapper.toDto(cDocumentoViaje);
        restCDocumentoViajeMockMvc.perform(post("/api/c-documento-viajes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cDocumentoViajeDTO)))
            .andExpect(status().isCreated());

        // Validate the CDocumentoViaje in the database
        List<CDocumentoViaje> cDocumentoViajeList = cDocumentoViajeRepository.findAll();
        assertThat(cDocumentoViajeList).hasSize(databaseSizeBeforeCreate + 1);
        CDocumentoViaje testCDocumentoViaje = cDocumentoViajeList.get(cDocumentoViajeList.size() - 1);
        assertThat(testCDocumentoViaje.getClaveDocumento()).isEqualTo(DEFAULT_CLAVE_DOCUMENTO);
        assertThat(testCDocumentoViaje.getDocumento()).isEqualTo(DEFAULT_DOCUMENTO);
        assertThat(testCDocumentoViaje.getIdUsuarioCreacion()).isEqualTo(DEFAULT_ID_USUARIO_CREACION);
        assertThat(testCDocumentoViaje.getFechaCreacion()).isEqualTo(DEFAULT_FECHA_CREACION);
        assertThat(testCDocumentoViaje.getIdUsuarioActualizacion()).isEqualTo(DEFAULT_ID_USUARIO_ACTUALIZACION);
        assertThat(testCDocumentoViaje.getFechaActualizacion()).isEqualTo(DEFAULT_FECHA_ACTUALIZACION);
        assertThat(testCDocumentoViaje.getNotas()).isEqualTo(DEFAULT_NOTAS);
        assertThat(testCDocumentoViaje.getEstatus()).isEqualTo(DEFAULT_ESTATUS);
        assertThat(testCDocumentoViaje.getBorrado()).isEqualTo(DEFAULT_BORRADO);
    }

    @Test
    @Transactional
    public void createCDocumentoViajeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cDocumentoViajeRepository.findAll().size();

        // Create the CDocumentoViaje with an existing ID
        cDocumentoViaje.setId(1L);
        CDocumentoViajeDTO cDocumentoViajeDTO = cDocumentoViajeMapper.toDto(cDocumentoViaje);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCDocumentoViajeMockMvc.perform(post("/api/c-documento-viajes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cDocumentoViajeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CDocumentoViaje in the database
        List<CDocumentoViaje> cDocumentoViajeList = cDocumentoViajeRepository.findAll();
        assertThat(cDocumentoViajeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdUsuarioCreacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = cDocumentoViajeRepository.findAll().size();
        // set the field null
        cDocumentoViaje.setIdUsuarioCreacion(null);

        // Create the CDocumentoViaje, which fails.
        CDocumentoViajeDTO cDocumentoViajeDTO = cDocumentoViajeMapper.toDto(cDocumentoViaje);

        restCDocumentoViajeMockMvc.perform(post("/api/c-documento-viajes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cDocumentoViajeDTO)))
            .andExpect(status().isBadRequest());

        List<CDocumentoViaje> cDocumentoViajeList = cDocumentoViajeRepository.findAll();
        assertThat(cDocumentoViajeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFechaCreacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = cDocumentoViajeRepository.findAll().size();
        // set the field null
        cDocumentoViaje.setFechaCreacion(null);

        // Create the CDocumentoViaje, which fails.
        CDocumentoViajeDTO cDocumentoViajeDTO = cDocumentoViajeMapper.toDto(cDocumentoViaje);

        restCDocumentoViajeMockMvc.perform(post("/api/c-documento-viajes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cDocumentoViajeDTO)))
            .andExpect(status().isBadRequest());

        List<CDocumentoViaje> cDocumentoViajeList = cDocumentoViajeRepository.findAll();
        assertThat(cDocumentoViajeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = cDocumentoViajeRepository.findAll().size();
        // set the field null
        cDocumentoViaje.setEstatus(null);

        // Create the CDocumentoViaje, which fails.
        CDocumentoViajeDTO cDocumentoViajeDTO = cDocumentoViajeMapper.toDto(cDocumentoViaje);

        restCDocumentoViajeMockMvc.perform(post("/api/c-documento-viajes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cDocumentoViajeDTO)))
            .andExpect(status().isBadRequest());

        List<CDocumentoViaje> cDocumentoViajeList = cDocumentoViajeRepository.findAll();
        assertThat(cDocumentoViajeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBorradoIsRequired() throws Exception {
        int databaseSizeBeforeTest = cDocumentoViajeRepository.findAll().size();
        // set the field null
        cDocumentoViaje.setBorrado(null);

        // Create the CDocumentoViaje, which fails.
        CDocumentoViajeDTO cDocumentoViajeDTO = cDocumentoViajeMapper.toDto(cDocumentoViaje);

        restCDocumentoViajeMockMvc.perform(post("/api/c-documento-viajes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cDocumentoViajeDTO)))
            .andExpect(status().isBadRequest());

        List<CDocumentoViaje> cDocumentoViajeList = cDocumentoViajeRepository.findAll();
        assertThat(cDocumentoViajeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajes() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList
        restCDocumentoViajeMockMvc.perform(get("/api/c-documento-viajes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cDocumentoViaje.getId().intValue())))
            .andExpect(jsonPath("$.[*].claveDocumento").value(hasItem(DEFAULT_CLAVE_DOCUMENTO)))
            .andExpect(jsonPath("$.[*].documento").value(hasItem(DEFAULT_DOCUMENTO)))
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
    public void getCDocumentoViaje() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get the cDocumentoViaje
        restCDocumentoViajeMockMvc.perform(get("/api/c-documento-viajes/{id}", cDocumentoViaje.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cDocumentoViaje.getId().intValue()))
            .andExpect(jsonPath("$.claveDocumento").value(DEFAULT_CLAVE_DOCUMENTO))
            .andExpect(jsonPath("$.documento").value(DEFAULT_DOCUMENTO))
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
    public void getCDocumentoViajesByIdFiltering() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        Long id = cDocumentoViaje.getId();

        defaultCDocumentoViajeShouldBeFound("id.equals=" + id);
        defaultCDocumentoViajeShouldNotBeFound("id.notEquals=" + id);

        defaultCDocumentoViajeShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCDocumentoViajeShouldNotBeFound("id.greaterThan=" + id);

        defaultCDocumentoViajeShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCDocumentoViajeShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllCDocumentoViajesByClaveDocumentoIsEqualToSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where claveDocumento equals to DEFAULT_CLAVE_DOCUMENTO
        defaultCDocumentoViajeShouldBeFound("claveDocumento.equals=" + DEFAULT_CLAVE_DOCUMENTO);

        // Get all the cDocumentoViajeList where claveDocumento equals to UPDATED_CLAVE_DOCUMENTO
        defaultCDocumentoViajeShouldNotBeFound("claveDocumento.equals=" + UPDATED_CLAVE_DOCUMENTO);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByClaveDocumentoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where claveDocumento not equals to DEFAULT_CLAVE_DOCUMENTO
        defaultCDocumentoViajeShouldNotBeFound("claveDocumento.notEquals=" + DEFAULT_CLAVE_DOCUMENTO);

        // Get all the cDocumentoViajeList where claveDocumento not equals to UPDATED_CLAVE_DOCUMENTO
        defaultCDocumentoViajeShouldBeFound("claveDocumento.notEquals=" + UPDATED_CLAVE_DOCUMENTO);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByClaveDocumentoIsInShouldWork() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where claveDocumento in DEFAULT_CLAVE_DOCUMENTO or UPDATED_CLAVE_DOCUMENTO
        defaultCDocumentoViajeShouldBeFound("claveDocumento.in=" + DEFAULT_CLAVE_DOCUMENTO + "," + UPDATED_CLAVE_DOCUMENTO);

        // Get all the cDocumentoViajeList where claveDocumento equals to UPDATED_CLAVE_DOCUMENTO
        defaultCDocumentoViajeShouldNotBeFound("claveDocumento.in=" + UPDATED_CLAVE_DOCUMENTO);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByClaveDocumentoIsNullOrNotNull() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where claveDocumento is not null
        defaultCDocumentoViajeShouldBeFound("claveDocumento.specified=true");

        // Get all the cDocumentoViajeList where claveDocumento is null
        defaultCDocumentoViajeShouldNotBeFound("claveDocumento.specified=false");
    }
                @Test
    @Transactional
    public void getAllCDocumentoViajesByClaveDocumentoContainsSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where claveDocumento contains DEFAULT_CLAVE_DOCUMENTO
        defaultCDocumentoViajeShouldBeFound("claveDocumento.contains=" + DEFAULT_CLAVE_DOCUMENTO);

        // Get all the cDocumentoViajeList where claveDocumento contains UPDATED_CLAVE_DOCUMENTO
        defaultCDocumentoViajeShouldNotBeFound("claveDocumento.contains=" + UPDATED_CLAVE_DOCUMENTO);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByClaveDocumentoNotContainsSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where claveDocumento does not contain DEFAULT_CLAVE_DOCUMENTO
        defaultCDocumentoViajeShouldNotBeFound("claveDocumento.doesNotContain=" + DEFAULT_CLAVE_DOCUMENTO);

        // Get all the cDocumentoViajeList where claveDocumento does not contain UPDATED_CLAVE_DOCUMENTO
        defaultCDocumentoViajeShouldBeFound("claveDocumento.doesNotContain=" + UPDATED_CLAVE_DOCUMENTO);
    }


    @Test
    @Transactional
    public void getAllCDocumentoViajesByDocumentoIsEqualToSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where documento equals to DEFAULT_DOCUMENTO
        defaultCDocumentoViajeShouldBeFound("documento.equals=" + DEFAULT_DOCUMENTO);

        // Get all the cDocumentoViajeList where documento equals to UPDATED_DOCUMENTO
        defaultCDocumentoViajeShouldNotBeFound("documento.equals=" + UPDATED_DOCUMENTO);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByDocumentoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where documento not equals to DEFAULT_DOCUMENTO
        defaultCDocumentoViajeShouldNotBeFound("documento.notEquals=" + DEFAULT_DOCUMENTO);

        // Get all the cDocumentoViajeList where documento not equals to UPDATED_DOCUMENTO
        defaultCDocumentoViajeShouldBeFound("documento.notEquals=" + UPDATED_DOCUMENTO);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByDocumentoIsInShouldWork() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where documento in DEFAULT_DOCUMENTO or UPDATED_DOCUMENTO
        defaultCDocumentoViajeShouldBeFound("documento.in=" + DEFAULT_DOCUMENTO + "," + UPDATED_DOCUMENTO);

        // Get all the cDocumentoViajeList where documento equals to UPDATED_DOCUMENTO
        defaultCDocumentoViajeShouldNotBeFound("documento.in=" + UPDATED_DOCUMENTO);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByDocumentoIsNullOrNotNull() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where documento is not null
        defaultCDocumentoViajeShouldBeFound("documento.specified=true");

        // Get all the cDocumentoViajeList where documento is null
        defaultCDocumentoViajeShouldNotBeFound("documento.specified=false");
    }
                @Test
    @Transactional
    public void getAllCDocumentoViajesByDocumentoContainsSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where documento contains DEFAULT_DOCUMENTO
        defaultCDocumentoViajeShouldBeFound("documento.contains=" + DEFAULT_DOCUMENTO);

        // Get all the cDocumentoViajeList where documento contains UPDATED_DOCUMENTO
        defaultCDocumentoViajeShouldNotBeFound("documento.contains=" + UPDATED_DOCUMENTO);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByDocumentoNotContainsSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where documento does not contain DEFAULT_DOCUMENTO
        defaultCDocumentoViajeShouldNotBeFound("documento.doesNotContain=" + DEFAULT_DOCUMENTO);

        // Get all the cDocumentoViajeList where documento does not contain UPDATED_DOCUMENTO
        defaultCDocumentoViajeShouldBeFound("documento.doesNotContain=" + UPDATED_DOCUMENTO);
    }


    @Test
    @Transactional
    public void getAllCDocumentoViajesByIdUsuarioCreacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where idUsuarioCreacion equals to DEFAULT_ID_USUARIO_CREACION
        defaultCDocumentoViajeShouldBeFound("idUsuarioCreacion.equals=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cDocumentoViajeList where idUsuarioCreacion equals to UPDATED_ID_USUARIO_CREACION
        defaultCDocumentoViajeShouldNotBeFound("idUsuarioCreacion.equals=" + UPDATED_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByIdUsuarioCreacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where idUsuarioCreacion not equals to DEFAULT_ID_USUARIO_CREACION
        defaultCDocumentoViajeShouldNotBeFound("idUsuarioCreacion.notEquals=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cDocumentoViajeList where idUsuarioCreacion not equals to UPDATED_ID_USUARIO_CREACION
        defaultCDocumentoViajeShouldBeFound("idUsuarioCreacion.notEquals=" + UPDATED_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByIdUsuarioCreacionIsInShouldWork() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where idUsuarioCreacion in DEFAULT_ID_USUARIO_CREACION or UPDATED_ID_USUARIO_CREACION
        defaultCDocumentoViajeShouldBeFound("idUsuarioCreacion.in=" + DEFAULT_ID_USUARIO_CREACION + "," + UPDATED_ID_USUARIO_CREACION);

        // Get all the cDocumentoViajeList where idUsuarioCreacion equals to UPDATED_ID_USUARIO_CREACION
        defaultCDocumentoViajeShouldNotBeFound("idUsuarioCreacion.in=" + UPDATED_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByIdUsuarioCreacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where idUsuarioCreacion is not null
        defaultCDocumentoViajeShouldBeFound("idUsuarioCreacion.specified=true");

        // Get all the cDocumentoViajeList where idUsuarioCreacion is null
        defaultCDocumentoViajeShouldNotBeFound("idUsuarioCreacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByIdUsuarioCreacionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where idUsuarioCreacion is greater than or equal to DEFAULT_ID_USUARIO_CREACION
        defaultCDocumentoViajeShouldBeFound("idUsuarioCreacion.greaterThanOrEqual=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cDocumentoViajeList where idUsuarioCreacion is greater than or equal to (DEFAULT_ID_USUARIO_CREACION + 1)
        defaultCDocumentoViajeShouldNotBeFound("idUsuarioCreacion.greaterThanOrEqual=" + (DEFAULT_ID_USUARIO_CREACION + 1));
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByIdUsuarioCreacionIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where idUsuarioCreacion is less than or equal to DEFAULT_ID_USUARIO_CREACION
        defaultCDocumentoViajeShouldBeFound("idUsuarioCreacion.lessThanOrEqual=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cDocumentoViajeList where idUsuarioCreacion is less than or equal to SMALLER_ID_USUARIO_CREACION
        defaultCDocumentoViajeShouldNotBeFound("idUsuarioCreacion.lessThanOrEqual=" + SMALLER_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByIdUsuarioCreacionIsLessThanSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where idUsuarioCreacion is less than DEFAULT_ID_USUARIO_CREACION
        defaultCDocumentoViajeShouldNotBeFound("idUsuarioCreacion.lessThan=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cDocumentoViajeList where idUsuarioCreacion is less than (DEFAULT_ID_USUARIO_CREACION + 1)
        defaultCDocumentoViajeShouldBeFound("idUsuarioCreacion.lessThan=" + (DEFAULT_ID_USUARIO_CREACION + 1));
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByIdUsuarioCreacionIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where idUsuarioCreacion is greater than DEFAULT_ID_USUARIO_CREACION
        defaultCDocumentoViajeShouldNotBeFound("idUsuarioCreacion.greaterThan=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cDocumentoViajeList where idUsuarioCreacion is greater than SMALLER_ID_USUARIO_CREACION
        defaultCDocumentoViajeShouldBeFound("idUsuarioCreacion.greaterThan=" + SMALLER_ID_USUARIO_CREACION);
    }


    @Test
    @Transactional
    public void getAllCDocumentoViajesByFechaCreacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where fechaCreacion equals to DEFAULT_FECHA_CREACION
        defaultCDocumentoViajeShouldBeFound("fechaCreacion.equals=" + DEFAULT_FECHA_CREACION);

        // Get all the cDocumentoViajeList where fechaCreacion equals to UPDATED_FECHA_CREACION
        defaultCDocumentoViajeShouldNotBeFound("fechaCreacion.equals=" + UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByFechaCreacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where fechaCreacion not equals to DEFAULT_FECHA_CREACION
        defaultCDocumentoViajeShouldNotBeFound("fechaCreacion.notEquals=" + DEFAULT_FECHA_CREACION);

        // Get all the cDocumentoViajeList where fechaCreacion not equals to UPDATED_FECHA_CREACION
        defaultCDocumentoViajeShouldBeFound("fechaCreacion.notEquals=" + UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByFechaCreacionIsInShouldWork() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where fechaCreacion in DEFAULT_FECHA_CREACION or UPDATED_FECHA_CREACION
        defaultCDocumentoViajeShouldBeFound("fechaCreacion.in=" + DEFAULT_FECHA_CREACION + "," + UPDATED_FECHA_CREACION);

        // Get all the cDocumentoViajeList where fechaCreacion equals to UPDATED_FECHA_CREACION
        defaultCDocumentoViajeShouldNotBeFound("fechaCreacion.in=" + UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByFechaCreacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where fechaCreacion is not null
        defaultCDocumentoViajeShouldBeFound("fechaCreacion.specified=true");

        // Get all the cDocumentoViajeList where fechaCreacion is null
        defaultCDocumentoViajeShouldNotBeFound("fechaCreacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByIdUsuarioActualizacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where idUsuarioActualizacion equals to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCDocumentoViajeShouldBeFound("idUsuarioActualizacion.equals=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cDocumentoViajeList where idUsuarioActualizacion equals to UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCDocumentoViajeShouldNotBeFound("idUsuarioActualizacion.equals=" + UPDATED_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByIdUsuarioActualizacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where idUsuarioActualizacion not equals to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCDocumentoViajeShouldNotBeFound("idUsuarioActualizacion.notEquals=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cDocumentoViajeList where idUsuarioActualizacion not equals to UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCDocumentoViajeShouldBeFound("idUsuarioActualizacion.notEquals=" + UPDATED_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByIdUsuarioActualizacionIsInShouldWork() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where idUsuarioActualizacion in DEFAULT_ID_USUARIO_ACTUALIZACION or UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCDocumentoViajeShouldBeFound("idUsuarioActualizacion.in=" + DEFAULT_ID_USUARIO_ACTUALIZACION + "," + UPDATED_ID_USUARIO_ACTUALIZACION);

        // Get all the cDocumentoViajeList where idUsuarioActualizacion equals to UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCDocumentoViajeShouldNotBeFound("idUsuarioActualizacion.in=" + UPDATED_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByIdUsuarioActualizacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where idUsuarioActualizacion is not null
        defaultCDocumentoViajeShouldBeFound("idUsuarioActualizacion.specified=true");

        // Get all the cDocumentoViajeList where idUsuarioActualizacion is null
        defaultCDocumentoViajeShouldNotBeFound("idUsuarioActualizacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByIdUsuarioActualizacionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where idUsuarioActualizacion is greater than or equal to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCDocumentoViajeShouldBeFound("idUsuarioActualizacion.greaterThanOrEqual=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cDocumentoViajeList where idUsuarioActualizacion is greater than or equal to (DEFAULT_ID_USUARIO_ACTUALIZACION + 1)
        defaultCDocumentoViajeShouldNotBeFound("idUsuarioActualizacion.greaterThanOrEqual=" + (DEFAULT_ID_USUARIO_ACTUALIZACION + 1));
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByIdUsuarioActualizacionIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where idUsuarioActualizacion is less than or equal to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCDocumentoViajeShouldBeFound("idUsuarioActualizacion.lessThanOrEqual=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cDocumentoViajeList where idUsuarioActualizacion is less than or equal to SMALLER_ID_USUARIO_ACTUALIZACION
        defaultCDocumentoViajeShouldNotBeFound("idUsuarioActualizacion.lessThanOrEqual=" + SMALLER_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByIdUsuarioActualizacionIsLessThanSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where idUsuarioActualizacion is less than DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCDocumentoViajeShouldNotBeFound("idUsuarioActualizacion.lessThan=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cDocumentoViajeList where idUsuarioActualizacion is less than (DEFAULT_ID_USUARIO_ACTUALIZACION + 1)
        defaultCDocumentoViajeShouldBeFound("idUsuarioActualizacion.lessThan=" + (DEFAULT_ID_USUARIO_ACTUALIZACION + 1));
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByIdUsuarioActualizacionIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where idUsuarioActualizacion is greater than DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCDocumentoViajeShouldNotBeFound("idUsuarioActualizacion.greaterThan=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cDocumentoViajeList where idUsuarioActualizacion is greater than SMALLER_ID_USUARIO_ACTUALIZACION
        defaultCDocumentoViajeShouldBeFound("idUsuarioActualizacion.greaterThan=" + SMALLER_ID_USUARIO_ACTUALIZACION);
    }


    @Test
    @Transactional
    public void getAllCDocumentoViajesByFechaActualizacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where fechaActualizacion equals to DEFAULT_FECHA_ACTUALIZACION
        defaultCDocumentoViajeShouldBeFound("fechaActualizacion.equals=" + DEFAULT_FECHA_ACTUALIZACION);

        // Get all the cDocumentoViajeList where fechaActualizacion equals to UPDATED_FECHA_ACTUALIZACION
        defaultCDocumentoViajeShouldNotBeFound("fechaActualizacion.equals=" + UPDATED_FECHA_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByFechaActualizacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where fechaActualizacion not equals to DEFAULT_FECHA_ACTUALIZACION
        defaultCDocumentoViajeShouldNotBeFound("fechaActualizacion.notEquals=" + DEFAULT_FECHA_ACTUALIZACION);

        // Get all the cDocumentoViajeList where fechaActualizacion not equals to UPDATED_FECHA_ACTUALIZACION
        defaultCDocumentoViajeShouldBeFound("fechaActualizacion.notEquals=" + UPDATED_FECHA_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByFechaActualizacionIsInShouldWork() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where fechaActualizacion in DEFAULT_FECHA_ACTUALIZACION or UPDATED_FECHA_ACTUALIZACION
        defaultCDocumentoViajeShouldBeFound("fechaActualizacion.in=" + DEFAULT_FECHA_ACTUALIZACION + "," + UPDATED_FECHA_ACTUALIZACION);

        // Get all the cDocumentoViajeList where fechaActualizacion equals to UPDATED_FECHA_ACTUALIZACION
        defaultCDocumentoViajeShouldNotBeFound("fechaActualizacion.in=" + UPDATED_FECHA_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByFechaActualizacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where fechaActualizacion is not null
        defaultCDocumentoViajeShouldBeFound("fechaActualizacion.specified=true");

        // Get all the cDocumentoViajeList where fechaActualizacion is null
        defaultCDocumentoViajeShouldNotBeFound("fechaActualizacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByNotasIsEqualToSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where notas equals to DEFAULT_NOTAS
        defaultCDocumentoViajeShouldBeFound("notas.equals=" + DEFAULT_NOTAS);

        // Get all the cDocumentoViajeList where notas equals to UPDATED_NOTAS
        defaultCDocumentoViajeShouldNotBeFound("notas.equals=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByNotasIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where notas not equals to DEFAULT_NOTAS
        defaultCDocumentoViajeShouldNotBeFound("notas.notEquals=" + DEFAULT_NOTAS);

        // Get all the cDocumentoViajeList where notas not equals to UPDATED_NOTAS
        defaultCDocumentoViajeShouldBeFound("notas.notEquals=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByNotasIsInShouldWork() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where notas in DEFAULT_NOTAS or UPDATED_NOTAS
        defaultCDocumentoViajeShouldBeFound("notas.in=" + DEFAULT_NOTAS + "," + UPDATED_NOTAS);

        // Get all the cDocumentoViajeList where notas equals to UPDATED_NOTAS
        defaultCDocumentoViajeShouldNotBeFound("notas.in=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByNotasIsNullOrNotNull() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where notas is not null
        defaultCDocumentoViajeShouldBeFound("notas.specified=true");

        // Get all the cDocumentoViajeList where notas is null
        defaultCDocumentoViajeShouldNotBeFound("notas.specified=false");
    }
                @Test
    @Transactional
    public void getAllCDocumentoViajesByNotasContainsSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where notas contains DEFAULT_NOTAS
        defaultCDocumentoViajeShouldBeFound("notas.contains=" + DEFAULT_NOTAS);

        // Get all the cDocumentoViajeList where notas contains UPDATED_NOTAS
        defaultCDocumentoViajeShouldNotBeFound("notas.contains=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByNotasNotContainsSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where notas does not contain DEFAULT_NOTAS
        defaultCDocumentoViajeShouldNotBeFound("notas.doesNotContain=" + DEFAULT_NOTAS);

        // Get all the cDocumentoViajeList where notas does not contain UPDATED_NOTAS
        defaultCDocumentoViajeShouldBeFound("notas.doesNotContain=" + UPDATED_NOTAS);
    }


    @Test
    @Transactional
    public void getAllCDocumentoViajesByEstatusIsEqualToSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where estatus equals to DEFAULT_ESTATUS
        defaultCDocumentoViajeShouldBeFound("estatus.equals=" + DEFAULT_ESTATUS);

        // Get all the cDocumentoViajeList where estatus equals to UPDATED_ESTATUS
        defaultCDocumentoViajeShouldNotBeFound("estatus.equals=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByEstatusIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where estatus not equals to DEFAULT_ESTATUS
        defaultCDocumentoViajeShouldNotBeFound("estatus.notEquals=" + DEFAULT_ESTATUS);

        // Get all the cDocumentoViajeList where estatus not equals to UPDATED_ESTATUS
        defaultCDocumentoViajeShouldBeFound("estatus.notEquals=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByEstatusIsInShouldWork() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where estatus in DEFAULT_ESTATUS or UPDATED_ESTATUS
        defaultCDocumentoViajeShouldBeFound("estatus.in=" + DEFAULT_ESTATUS + "," + UPDATED_ESTATUS);

        // Get all the cDocumentoViajeList where estatus equals to UPDATED_ESTATUS
        defaultCDocumentoViajeShouldNotBeFound("estatus.in=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByEstatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where estatus is not null
        defaultCDocumentoViajeShouldBeFound("estatus.specified=true");

        // Get all the cDocumentoViajeList where estatus is null
        defaultCDocumentoViajeShouldNotBeFound("estatus.specified=false");
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByEstatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where estatus is greater than or equal to DEFAULT_ESTATUS
        defaultCDocumentoViajeShouldBeFound("estatus.greaterThanOrEqual=" + DEFAULT_ESTATUS);

        // Get all the cDocumentoViajeList where estatus is greater than or equal to (DEFAULT_ESTATUS + 1)
        defaultCDocumentoViajeShouldNotBeFound("estatus.greaterThanOrEqual=" + (DEFAULT_ESTATUS + 1));
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByEstatusIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where estatus is less than or equal to DEFAULT_ESTATUS
        defaultCDocumentoViajeShouldBeFound("estatus.lessThanOrEqual=" + DEFAULT_ESTATUS);

        // Get all the cDocumentoViajeList where estatus is less than or equal to SMALLER_ESTATUS
        defaultCDocumentoViajeShouldNotBeFound("estatus.lessThanOrEqual=" + SMALLER_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByEstatusIsLessThanSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where estatus is less than DEFAULT_ESTATUS
        defaultCDocumentoViajeShouldNotBeFound("estatus.lessThan=" + DEFAULT_ESTATUS);

        // Get all the cDocumentoViajeList where estatus is less than (DEFAULT_ESTATUS + 1)
        defaultCDocumentoViajeShouldBeFound("estatus.lessThan=" + (DEFAULT_ESTATUS + 1));
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByEstatusIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where estatus is greater than DEFAULT_ESTATUS
        defaultCDocumentoViajeShouldNotBeFound("estatus.greaterThan=" + DEFAULT_ESTATUS);

        // Get all the cDocumentoViajeList where estatus is greater than SMALLER_ESTATUS
        defaultCDocumentoViajeShouldBeFound("estatus.greaterThan=" + SMALLER_ESTATUS);
    }


    @Test
    @Transactional
    public void getAllCDocumentoViajesByBorradoIsEqualToSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where borrado equals to DEFAULT_BORRADO
        defaultCDocumentoViajeShouldBeFound("borrado.equals=" + DEFAULT_BORRADO);

        // Get all the cDocumentoViajeList where borrado equals to UPDATED_BORRADO
        defaultCDocumentoViajeShouldNotBeFound("borrado.equals=" + UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByBorradoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where borrado not equals to DEFAULT_BORRADO
        defaultCDocumentoViajeShouldNotBeFound("borrado.notEquals=" + DEFAULT_BORRADO);

        // Get all the cDocumentoViajeList where borrado not equals to UPDATED_BORRADO
        defaultCDocumentoViajeShouldBeFound("borrado.notEquals=" + UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByBorradoIsInShouldWork() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where borrado in DEFAULT_BORRADO or UPDATED_BORRADO
        defaultCDocumentoViajeShouldBeFound("borrado.in=" + DEFAULT_BORRADO + "," + UPDATED_BORRADO);

        // Get all the cDocumentoViajeList where borrado equals to UPDATED_BORRADO
        defaultCDocumentoViajeShouldNotBeFound("borrado.in=" + UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByBorradoIsNullOrNotNull() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where borrado is not null
        defaultCDocumentoViajeShouldBeFound("borrado.specified=true");

        // Get all the cDocumentoViajeList where borrado is null
        defaultCDocumentoViajeShouldNotBeFound("borrado.specified=false");
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByBorradoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where borrado is greater than or equal to DEFAULT_BORRADO
        defaultCDocumentoViajeShouldBeFound("borrado.greaterThanOrEqual=" + DEFAULT_BORRADO);

        // Get all the cDocumentoViajeList where borrado is greater than or equal to (DEFAULT_BORRADO + 1)
        defaultCDocumentoViajeShouldNotBeFound("borrado.greaterThanOrEqual=" + (DEFAULT_BORRADO + 1));
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByBorradoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where borrado is less than or equal to DEFAULT_BORRADO
        defaultCDocumentoViajeShouldBeFound("borrado.lessThanOrEqual=" + DEFAULT_BORRADO);

        // Get all the cDocumentoViajeList where borrado is less than or equal to SMALLER_BORRADO
        defaultCDocumentoViajeShouldNotBeFound("borrado.lessThanOrEqual=" + SMALLER_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByBorradoIsLessThanSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where borrado is less than DEFAULT_BORRADO
        defaultCDocumentoViajeShouldNotBeFound("borrado.lessThan=" + DEFAULT_BORRADO);

        // Get all the cDocumentoViajeList where borrado is less than (DEFAULT_BORRADO + 1)
        defaultCDocumentoViajeShouldBeFound("borrado.lessThan=" + (DEFAULT_BORRADO + 1));
    }

    @Test
    @Transactional
    public void getAllCDocumentoViajesByBorradoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        // Get all the cDocumentoViajeList where borrado is greater than DEFAULT_BORRADO
        defaultCDocumentoViajeShouldNotBeFound("borrado.greaterThan=" + DEFAULT_BORRADO);

        // Get all the cDocumentoViajeList where borrado is greater than SMALLER_BORRADO
        defaultCDocumentoViajeShouldBeFound("borrado.greaterThan=" + SMALLER_BORRADO);
    }


    @Test
    @Transactional
    public void getAllCDocumentoViajesByClienteIsEqualToSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);
        CCliente cliente = CClienteResourceIT.createEntity(em);
        em.persist(cliente);
        em.flush();
        cDocumentoViaje.setCliente(cliente);
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);
        Long clienteId = cliente.getId();

        // Get all the cDocumentoViajeList where cliente equals to clienteId
        defaultCDocumentoViajeShouldBeFound("clienteId.equals=" + clienteId);

        // Get all the cDocumentoViajeList where cliente equals to clienteId + 1
        defaultCDocumentoViajeShouldNotBeFound("clienteId.equals=" + (clienteId + 1));
    }


    @Test
    @Transactional
    public void getAllCDocumentoViajesByPasajeroIsEqualToSomething() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);
        CPasajero pasajero = CPasajeroResourceIT.createEntity(em);
        em.persist(pasajero);
        em.flush();
        cDocumentoViaje.setPasajero(pasajero);
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);
        Long pasajeroId = pasajero.getId();

        // Get all the cDocumentoViajeList where pasajero equals to pasajeroId
        defaultCDocumentoViajeShouldBeFound("pasajeroId.equals=" + pasajeroId);

        // Get all the cDocumentoViajeList where pasajero equals to pasajeroId + 1
        defaultCDocumentoViajeShouldNotBeFound("pasajeroId.equals=" + (pasajeroId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCDocumentoViajeShouldBeFound(String filter) throws Exception {
        restCDocumentoViajeMockMvc.perform(get("/api/c-documento-viajes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cDocumentoViaje.getId().intValue())))
            .andExpect(jsonPath("$.[*].claveDocumento").value(hasItem(DEFAULT_CLAVE_DOCUMENTO)))
            .andExpect(jsonPath("$.[*].documento").value(hasItem(DEFAULT_DOCUMENTO)))
            .andExpect(jsonPath("$.[*].idUsuarioCreacion").value(hasItem(DEFAULT_ID_USUARIO_CREACION.intValue())))
            .andExpect(jsonPath("$.[*].fechaCreacion").value(hasItem(DEFAULT_FECHA_CREACION.toString())))
            .andExpect(jsonPath("$.[*].idUsuarioActualizacion").value(hasItem(DEFAULT_ID_USUARIO_ACTUALIZACION.intValue())))
            .andExpect(jsonPath("$.[*].fechaActualizacion").value(hasItem(DEFAULT_FECHA_ACTUALIZACION.toString())))
            .andExpect(jsonPath("$.[*].notas").value(hasItem(DEFAULT_NOTAS)))
            .andExpect(jsonPath("$.[*].estatus").value(hasItem(DEFAULT_ESTATUS)))
            .andExpect(jsonPath("$.[*].borrado").value(hasItem(DEFAULT_BORRADO)));

        // Check, that the count call also returns 1
        restCDocumentoViajeMockMvc.perform(get("/api/c-documento-viajes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCDocumentoViajeShouldNotBeFound(String filter) throws Exception {
        restCDocumentoViajeMockMvc.perform(get("/api/c-documento-viajes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCDocumentoViajeMockMvc.perform(get("/api/c-documento-viajes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCDocumentoViaje() throws Exception {
        // Get the cDocumentoViaje
        restCDocumentoViajeMockMvc.perform(get("/api/c-documento-viajes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCDocumentoViaje() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        int databaseSizeBeforeUpdate = cDocumentoViajeRepository.findAll().size();

        // Update the cDocumentoViaje
        CDocumentoViaje updatedCDocumentoViaje = cDocumentoViajeRepository.findById(cDocumentoViaje.getId()).get();
        // Disconnect from session so that the updates on updatedCDocumentoViaje are not directly saved in db
        em.detach(updatedCDocumentoViaje);
        updatedCDocumentoViaje
            .claveDocumento(UPDATED_CLAVE_DOCUMENTO)
            .documento(UPDATED_DOCUMENTO)
            .idUsuarioCreacion(UPDATED_ID_USUARIO_CREACION)
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .idUsuarioActualizacion(UPDATED_ID_USUARIO_ACTUALIZACION)
            .fechaActualizacion(UPDATED_FECHA_ACTUALIZACION)
            .notas(UPDATED_NOTAS)
            .estatus(UPDATED_ESTATUS)
            .borrado(UPDATED_BORRADO);
        CDocumentoViajeDTO cDocumentoViajeDTO = cDocumentoViajeMapper.toDto(updatedCDocumentoViaje);

        restCDocumentoViajeMockMvc.perform(put("/api/c-documento-viajes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cDocumentoViajeDTO)))
            .andExpect(status().isOk());

        // Validate the CDocumentoViaje in the database
        List<CDocumentoViaje> cDocumentoViajeList = cDocumentoViajeRepository.findAll();
        assertThat(cDocumentoViajeList).hasSize(databaseSizeBeforeUpdate);
        CDocumentoViaje testCDocumentoViaje = cDocumentoViajeList.get(cDocumentoViajeList.size() - 1);
        assertThat(testCDocumentoViaje.getClaveDocumento()).isEqualTo(UPDATED_CLAVE_DOCUMENTO);
        assertThat(testCDocumentoViaje.getDocumento()).isEqualTo(UPDATED_DOCUMENTO);
        assertThat(testCDocumentoViaje.getIdUsuarioCreacion()).isEqualTo(UPDATED_ID_USUARIO_CREACION);
        assertThat(testCDocumentoViaje.getFechaCreacion()).isEqualTo(UPDATED_FECHA_CREACION);
        assertThat(testCDocumentoViaje.getIdUsuarioActualizacion()).isEqualTo(UPDATED_ID_USUARIO_ACTUALIZACION);
        assertThat(testCDocumentoViaje.getFechaActualizacion()).isEqualTo(UPDATED_FECHA_ACTUALIZACION);
        assertThat(testCDocumentoViaje.getNotas()).isEqualTo(UPDATED_NOTAS);
        assertThat(testCDocumentoViaje.getEstatus()).isEqualTo(UPDATED_ESTATUS);
        assertThat(testCDocumentoViaje.getBorrado()).isEqualTo(UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void updateNonExistingCDocumentoViaje() throws Exception {
        int databaseSizeBeforeUpdate = cDocumentoViajeRepository.findAll().size();

        // Create the CDocumentoViaje
        CDocumentoViajeDTO cDocumentoViajeDTO = cDocumentoViajeMapper.toDto(cDocumentoViaje);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCDocumentoViajeMockMvc.perform(put("/api/c-documento-viajes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cDocumentoViajeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CDocumentoViaje in the database
        List<CDocumentoViaje> cDocumentoViajeList = cDocumentoViajeRepository.findAll();
        assertThat(cDocumentoViajeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCDocumentoViaje() throws Exception {
        // Initialize the database
        cDocumentoViajeRepository.saveAndFlush(cDocumentoViaje);

        int databaseSizeBeforeDelete = cDocumentoViajeRepository.findAll().size();

        // Delete the cDocumentoViaje
        restCDocumentoViajeMockMvc.perform(delete("/api/c-documento-viajes/{id}", cDocumentoViaje.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CDocumentoViaje> cDocumentoViajeList = cDocumentoViajeRepository.findAll();
        assertThat(cDocumentoViajeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
