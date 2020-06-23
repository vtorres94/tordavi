package mx.com.tordavi.web.rest;

import mx.com.tordavi.TordaviApp;
import mx.com.tordavi.domain.CContacto;
import mx.com.tordavi.domain.CCliente;
import mx.com.tordavi.domain.CPasajero;
import mx.com.tordavi.repository.CContactoRepository;
import mx.com.tordavi.service.CContactoService;
import mx.com.tordavi.service.dto.CContactoDTO;
import mx.com.tordavi.service.mapper.CContactoMapper;
import mx.com.tordavi.service.dto.CContactoCriteria;
import mx.com.tordavi.service.CContactoQueryService;

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
 * Integration tests for the {@link CContactoResource} REST controller.
 */
@SpringBootTest(classes = TordaviApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class CContactoResourceIT {

    private static final String DEFAULT_NOMBRE_CONTACTO = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_CONTACTO = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONO = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONO = "BBBBBBBBBB";

    private static final String DEFAULT_AREA = "AAAAAAAAAA";
    private static final String UPDATED_AREA = "BBBBBBBBBB";

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
    private CContactoRepository cContactoRepository;

    @Autowired
    private CContactoMapper cContactoMapper;

    @Autowired
    private CContactoService cContactoService;

    @Autowired
    private CContactoQueryService cContactoQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCContactoMockMvc;

    private CContacto cContacto;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CContacto createEntity(EntityManager em) {
        CContacto cContacto = new CContacto()
            .nombreContacto(DEFAULT_NOMBRE_CONTACTO)
            .telefono(DEFAULT_TELEFONO)
            .area(DEFAULT_AREA)
            .idUsuarioCreacion(DEFAULT_ID_USUARIO_CREACION)
            .fechaCreacion(DEFAULT_FECHA_CREACION)
            .idUsuarioActualizacion(DEFAULT_ID_USUARIO_ACTUALIZACION)
            .fechaActualizacion(DEFAULT_FECHA_ACTUALIZACION)
            .notas(DEFAULT_NOTAS)
            .estatus(DEFAULT_ESTATUS)
            .borrado(DEFAULT_BORRADO);
        return cContacto;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CContacto createUpdatedEntity(EntityManager em) {
        CContacto cContacto = new CContacto()
            .nombreContacto(UPDATED_NOMBRE_CONTACTO)
            .telefono(UPDATED_TELEFONO)
            .area(UPDATED_AREA)
            .idUsuarioCreacion(UPDATED_ID_USUARIO_CREACION)
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .idUsuarioActualizacion(UPDATED_ID_USUARIO_ACTUALIZACION)
            .fechaActualizacion(UPDATED_FECHA_ACTUALIZACION)
            .notas(UPDATED_NOTAS)
            .estatus(UPDATED_ESTATUS)
            .borrado(UPDATED_BORRADO);
        return cContacto;
    }

    @BeforeEach
    public void initTest() {
        cContacto = createEntity(em);
    }

    @Test
    @Transactional
    public void createCContacto() throws Exception {
        int databaseSizeBeforeCreate = cContactoRepository.findAll().size();

        // Create the CContacto
        CContactoDTO cContactoDTO = cContactoMapper.toDto(cContacto);
        restCContactoMockMvc.perform(post("/api/c-contactos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cContactoDTO)))
            .andExpect(status().isCreated());

        // Validate the CContacto in the database
        List<CContacto> cContactoList = cContactoRepository.findAll();
        assertThat(cContactoList).hasSize(databaseSizeBeforeCreate + 1);
        CContacto testCContacto = cContactoList.get(cContactoList.size() - 1);
        assertThat(testCContacto.getNombreContacto()).isEqualTo(DEFAULT_NOMBRE_CONTACTO);
        assertThat(testCContacto.getTelefono()).isEqualTo(DEFAULT_TELEFONO);
        assertThat(testCContacto.getArea()).isEqualTo(DEFAULT_AREA);
        assertThat(testCContacto.getIdUsuarioCreacion()).isEqualTo(DEFAULT_ID_USUARIO_CREACION);
        assertThat(testCContacto.getFechaCreacion()).isEqualTo(DEFAULT_FECHA_CREACION);
        assertThat(testCContacto.getIdUsuarioActualizacion()).isEqualTo(DEFAULT_ID_USUARIO_ACTUALIZACION);
        assertThat(testCContacto.getFechaActualizacion()).isEqualTo(DEFAULT_FECHA_ACTUALIZACION);
        assertThat(testCContacto.getNotas()).isEqualTo(DEFAULT_NOTAS);
        assertThat(testCContacto.getEstatus()).isEqualTo(DEFAULT_ESTATUS);
        assertThat(testCContacto.getBorrado()).isEqualTo(DEFAULT_BORRADO);
    }

    @Test
    @Transactional
    public void createCContactoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cContactoRepository.findAll().size();

        // Create the CContacto with an existing ID
        cContacto.setId(1L);
        CContactoDTO cContactoDTO = cContactoMapper.toDto(cContacto);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCContactoMockMvc.perform(post("/api/c-contactos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cContactoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CContacto in the database
        List<CContacto> cContactoList = cContactoRepository.findAll();
        assertThat(cContactoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdUsuarioCreacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = cContactoRepository.findAll().size();
        // set the field null
        cContacto.setIdUsuarioCreacion(null);

        // Create the CContacto, which fails.
        CContactoDTO cContactoDTO = cContactoMapper.toDto(cContacto);

        restCContactoMockMvc.perform(post("/api/c-contactos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cContactoDTO)))
            .andExpect(status().isBadRequest());

        List<CContacto> cContactoList = cContactoRepository.findAll();
        assertThat(cContactoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFechaCreacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = cContactoRepository.findAll().size();
        // set the field null
        cContacto.setFechaCreacion(null);

        // Create the CContacto, which fails.
        CContactoDTO cContactoDTO = cContactoMapper.toDto(cContacto);

        restCContactoMockMvc.perform(post("/api/c-contactos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cContactoDTO)))
            .andExpect(status().isBadRequest());

        List<CContacto> cContactoList = cContactoRepository.findAll();
        assertThat(cContactoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = cContactoRepository.findAll().size();
        // set the field null
        cContacto.setEstatus(null);

        // Create the CContacto, which fails.
        CContactoDTO cContactoDTO = cContactoMapper.toDto(cContacto);

        restCContactoMockMvc.perform(post("/api/c-contactos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cContactoDTO)))
            .andExpect(status().isBadRequest());

        List<CContacto> cContactoList = cContactoRepository.findAll();
        assertThat(cContactoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBorradoIsRequired() throws Exception {
        int databaseSizeBeforeTest = cContactoRepository.findAll().size();
        // set the field null
        cContacto.setBorrado(null);

        // Create the CContacto, which fails.
        CContactoDTO cContactoDTO = cContactoMapper.toDto(cContacto);

        restCContactoMockMvc.perform(post("/api/c-contactos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cContactoDTO)))
            .andExpect(status().isBadRequest());

        List<CContacto> cContactoList = cContactoRepository.findAll();
        assertThat(cContactoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCContactos() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList
        restCContactoMockMvc.perform(get("/api/c-contactos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cContacto.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombreContacto").value(hasItem(DEFAULT_NOMBRE_CONTACTO)))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO)))
            .andExpect(jsonPath("$.[*].area").value(hasItem(DEFAULT_AREA)))
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
    public void getCContacto() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get the cContacto
        restCContactoMockMvc.perform(get("/api/c-contactos/{id}", cContacto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cContacto.getId().intValue()))
            .andExpect(jsonPath("$.nombreContacto").value(DEFAULT_NOMBRE_CONTACTO))
            .andExpect(jsonPath("$.telefono").value(DEFAULT_TELEFONO))
            .andExpect(jsonPath("$.area").value(DEFAULT_AREA))
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
    public void getCContactosByIdFiltering() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        Long id = cContacto.getId();

        defaultCContactoShouldBeFound("id.equals=" + id);
        defaultCContactoShouldNotBeFound("id.notEquals=" + id);

        defaultCContactoShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCContactoShouldNotBeFound("id.greaterThan=" + id);

        defaultCContactoShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCContactoShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllCContactosByNombreContactoIsEqualToSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where nombreContacto equals to DEFAULT_NOMBRE_CONTACTO
        defaultCContactoShouldBeFound("nombreContacto.equals=" + DEFAULT_NOMBRE_CONTACTO);

        // Get all the cContactoList where nombreContacto equals to UPDATED_NOMBRE_CONTACTO
        defaultCContactoShouldNotBeFound("nombreContacto.equals=" + UPDATED_NOMBRE_CONTACTO);
    }

    @Test
    @Transactional
    public void getAllCContactosByNombreContactoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where nombreContacto not equals to DEFAULT_NOMBRE_CONTACTO
        defaultCContactoShouldNotBeFound("nombreContacto.notEquals=" + DEFAULT_NOMBRE_CONTACTO);

        // Get all the cContactoList where nombreContacto not equals to UPDATED_NOMBRE_CONTACTO
        defaultCContactoShouldBeFound("nombreContacto.notEquals=" + UPDATED_NOMBRE_CONTACTO);
    }

    @Test
    @Transactional
    public void getAllCContactosByNombreContactoIsInShouldWork() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where nombreContacto in DEFAULT_NOMBRE_CONTACTO or UPDATED_NOMBRE_CONTACTO
        defaultCContactoShouldBeFound("nombreContacto.in=" + DEFAULT_NOMBRE_CONTACTO + "," + UPDATED_NOMBRE_CONTACTO);

        // Get all the cContactoList where nombreContacto equals to UPDATED_NOMBRE_CONTACTO
        defaultCContactoShouldNotBeFound("nombreContacto.in=" + UPDATED_NOMBRE_CONTACTO);
    }

    @Test
    @Transactional
    public void getAllCContactosByNombreContactoIsNullOrNotNull() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where nombreContacto is not null
        defaultCContactoShouldBeFound("nombreContacto.specified=true");

        // Get all the cContactoList where nombreContacto is null
        defaultCContactoShouldNotBeFound("nombreContacto.specified=false");
    }
                @Test
    @Transactional
    public void getAllCContactosByNombreContactoContainsSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where nombreContacto contains DEFAULT_NOMBRE_CONTACTO
        defaultCContactoShouldBeFound("nombreContacto.contains=" + DEFAULT_NOMBRE_CONTACTO);

        // Get all the cContactoList where nombreContacto contains UPDATED_NOMBRE_CONTACTO
        defaultCContactoShouldNotBeFound("nombreContacto.contains=" + UPDATED_NOMBRE_CONTACTO);
    }

    @Test
    @Transactional
    public void getAllCContactosByNombreContactoNotContainsSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where nombreContacto does not contain DEFAULT_NOMBRE_CONTACTO
        defaultCContactoShouldNotBeFound("nombreContacto.doesNotContain=" + DEFAULT_NOMBRE_CONTACTO);

        // Get all the cContactoList where nombreContacto does not contain UPDATED_NOMBRE_CONTACTO
        defaultCContactoShouldBeFound("nombreContacto.doesNotContain=" + UPDATED_NOMBRE_CONTACTO);
    }


    @Test
    @Transactional
    public void getAllCContactosByTelefonoIsEqualToSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where telefono equals to DEFAULT_TELEFONO
        defaultCContactoShouldBeFound("telefono.equals=" + DEFAULT_TELEFONO);

        // Get all the cContactoList where telefono equals to UPDATED_TELEFONO
        defaultCContactoShouldNotBeFound("telefono.equals=" + UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    public void getAllCContactosByTelefonoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where telefono not equals to DEFAULT_TELEFONO
        defaultCContactoShouldNotBeFound("telefono.notEquals=" + DEFAULT_TELEFONO);

        // Get all the cContactoList where telefono not equals to UPDATED_TELEFONO
        defaultCContactoShouldBeFound("telefono.notEquals=" + UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    public void getAllCContactosByTelefonoIsInShouldWork() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where telefono in DEFAULT_TELEFONO or UPDATED_TELEFONO
        defaultCContactoShouldBeFound("telefono.in=" + DEFAULT_TELEFONO + "," + UPDATED_TELEFONO);

        // Get all the cContactoList where telefono equals to UPDATED_TELEFONO
        defaultCContactoShouldNotBeFound("telefono.in=" + UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    public void getAllCContactosByTelefonoIsNullOrNotNull() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where telefono is not null
        defaultCContactoShouldBeFound("telefono.specified=true");

        // Get all the cContactoList where telefono is null
        defaultCContactoShouldNotBeFound("telefono.specified=false");
    }
                @Test
    @Transactional
    public void getAllCContactosByTelefonoContainsSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where telefono contains DEFAULT_TELEFONO
        defaultCContactoShouldBeFound("telefono.contains=" + DEFAULT_TELEFONO);

        // Get all the cContactoList where telefono contains UPDATED_TELEFONO
        defaultCContactoShouldNotBeFound("telefono.contains=" + UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    public void getAllCContactosByTelefonoNotContainsSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where telefono does not contain DEFAULT_TELEFONO
        defaultCContactoShouldNotBeFound("telefono.doesNotContain=" + DEFAULT_TELEFONO);

        // Get all the cContactoList where telefono does not contain UPDATED_TELEFONO
        defaultCContactoShouldBeFound("telefono.doesNotContain=" + UPDATED_TELEFONO);
    }


    @Test
    @Transactional
    public void getAllCContactosByAreaIsEqualToSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where area equals to DEFAULT_AREA
        defaultCContactoShouldBeFound("area.equals=" + DEFAULT_AREA);

        // Get all the cContactoList where area equals to UPDATED_AREA
        defaultCContactoShouldNotBeFound("area.equals=" + UPDATED_AREA);
    }

    @Test
    @Transactional
    public void getAllCContactosByAreaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where area not equals to DEFAULT_AREA
        defaultCContactoShouldNotBeFound("area.notEquals=" + DEFAULT_AREA);

        // Get all the cContactoList where area not equals to UPDATED_AREA
        defaultCContactoShouldBeFound("area.notEquals=" + UPDATED_AREA);
    }

    @Test
    @Transactional
    public void getAllCContactosByAreaIsInShouldWork() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where area in DEFAULT_AREA or UPDATED_AREA
        defaultCContactoShouldBeFound("area.in=" + DEFAULT_AREA + "," + UPDATED_AREA);

        // Get all the cContactoList where area equals to UPDATED_AREA
        defaultCContactoShouldNotBeFound("area.in=" + UPDATED_AREA);
    }

    @Test
    @Transactional
    public void getAllCContactosByAreaIsNullOrNotNull() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where area is not null
        defaultCContactoShouldBeFound("area.specified=true");

        // Get all the cContactoList where area is null
        defaultCContactoShouldNotBeFound("area.specified=false");
    }
                @Test
    @Transactional
    public void getAllCContactosByAreaContainsSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where area contains DEFAULT_AREA
        defaultCContactoShouldBeFound("area.contains=" + DEFAULT_AREA);

        // Get all the cContactoList where area contains UPDATED_AREA
        defaultCContactoShouldNotBeFound("area.contains=" + UPDATED_AREA);
    }

    @Test
    @Transactional
    public void getAllCContactosByAreaNotContainsSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where area does not contain DEFAULT_AREA
        defaultCContactoShouldNotBeFound("area.doesNotContain=" + DEFAULT_AREA);

        // Get all the cContactoList where area does not contain UPDATED_AREA
        defaultCContactoShouldBeFound("area.doesNotContain=" + UPDATED_AREA);
    }


    @Test
    @Transactional
    public void getAllCContactosByIdUsuarioCreacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where idUsuarioCreacion equals to DEFAULT_ID_USUARIO_CREACION
        defaultCContactoShouldBeFound("idUsuarioCreacion.equals=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cContactoList where idUsuarioCreacion equals to UPDATED_ID_USUARIO_CREACION
        defaultCContactoShouldNotBeFound("idUsuarioCreacion.equals=" + UPDATED_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCContactosByIdUsuarioCreacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where idUsuarioCreacion not equals to DEFAULT_ID_USUARIO_CREACION
        defaultCContactoShouldNotBeFound("idUsuarioCreacion.notEquals=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cContactoList where idUsuarioCreacion not equals to UPDATED_ID_USUARIO_CREACION
        defaultCContactoShouldBeFound("idUsuarioCreacion.notEquals=" + UPDATED_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCContactosByIdUsuarioCreacionIsInShouldWork() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where idUsuarioCreacion in DEFAULT_ID_USUARIO_CREACION or UPDATED_ID_USUARIO_CREACION
        defaultCContactoShouldBeFound("idUsuarioCreacion.in=" + DEFAULT_ID_USUARIO_CREACION + "," + UPDATED_ID_USUARIO_CREACION);

        // Get all the cContactoList where idUsuarioCreacion equals to UPDATED_ID_USUARIO_CREACION
        defaultCContactoShouldNotBeFound("idUsuarioCreacion.in=" + UPDATED_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCContactosByIdUsuarioCreacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where idUsuarioCreacion is not null
        defaultCContactoShouldBeFound("idUsuarioCreacion.specified=true");

        // Get all the cContactoList where idUsuarioCreacion is null
        defaultCContactoShouldNotBeFound("idUsuarioCreacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCContactosByIdUsuarioCreacionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where idUsuarioCreacion is greater than or equal to DEFAULT_ID_USUARIO_CREACION
        defaultCContactoShouldBeFound("idUsuarioCreacion.greaterThanOrEqual=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cContactoList where idUsuarioCreacion is greater than or equal to (DEFAULT_ID_USUARIO_CREACION + 1)
        defaultCContactoShouldNotBeFound("idUsuarioCreacion.greaterThanOrEqual=" + (DEFAULT_ID_USUARIO_CREACION + 1));
    }

    @Test
    @Transactional
    public void getAllCContactosByIdUsuarioCreacionIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where idUsuarioCreacion is less than or equal to DEFAULT_ID_USUARIO_CREACION
        defaultCContactoShouldBeFound("idUsuarioCreacion.lessThanOrEqual=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cContactoList where idUsuarioCreacion is less than or equal to SMALLER_ID_USUARIO_CREACION
        defaultCContactoShouldNotBeFound("idUsuarioCreacion.lessThanOrEqual=" + SMALLER_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCContactosByIdUsuarioCreacionIsLessThanSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where idUsuarioCreacion is less than DEFAULT_ID_USUARIO_CREACION
        defaultCContactoShouldNotBeFound("idUsuarioCreacion.lessThan=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cContactoList where idUsuarioCreacion is less than (DEFAULT_ID_USUARIO_CREACION + 1)
        defaultCContactoShouldBeFound("idUsuarioCreacion.lessThan=" + (DEFAULT_ID_USUARIO_CREACION + 1));
    }

    @Test
    @Transactional
    public void getAllCContactosByIdUsuarioCreacionIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where idUsuarioCreacion is greater than DEFAULT_ID_USUARIO_CREACION
        defaultCContactoShouldNotBeFound("idUsuarioCreacion.greaterThan=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cContactoList where idUsuarioCreacion is greater than SMALLER_ID_USUARIO_CREACION
        defaultCContactoShouldBeFound("idUsuarioCreacion.greaterThan=" + SMALLER_ID_USUARIO_CREACION);
    }


    @Test
    @Transactional
    public void getAllCContactosByFechaCreacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where fechaCreacion equals to DEFAULT_FECHA_CREACION
        defaultCContactoShouldBeFound("fechaCreacion.equals=" + DEFAULT_FECHA_CREACION);

        // Get all the cContactoList where fechaCreacion equals to UPDATED_FECHA_CREACION
        defaultCContactoShouldNotBeFound("fechaCreacion.equals=" + UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void getAllCContactosByFechaCreacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where fechaCreacion not equals to DEFAULT_FECHA_CREACION
        defaultCContactoShouldNotBeFound("fechaCreacion.notEquals=" + DEFAULT_FECHA_CREACION);

        // Get all the cContactoList where fechaCreacion not equals to UPDATED_FECHA_CREACION
        defaultCContactoShouldBeFound("fechaCreacion.notEquals=" + UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void getAllCContactosByFechaCreacionIsInShouldWork() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where fechaCreacion in DEFAULT_FECHA_CREACION or UPDATED_FECHA_CREACION
        defaultCContactoShouldBeFound("fechaCreacion.in=" + DEFAULT_FECHA_CREACION + "," + UPDATED_FECHA_CREACION);

        // Get all the cContactoList where fechaCreacion equals to UPDATED_FECHA_CREACION
        defaultCContactoShouldNotBeFound("fechaCreacion.in=" + UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void getAllCContactosByFechaCreacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where fechaCreacion is not null
        defaultCContactoShouldBeFound("fechaCreacion.specified=true");

        // Get all the cContactoList where fechaCreacion is null
        defaultCContactoShouldNotBeFound("fechaCreacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCContactosByIdUsuarioActualizacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where idUsuarioActualizacion equals to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCContactoShouldBeFound("idUsuarioActualizacion.equals=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cContactoList where idUsuarioActualizacion equals to UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCContactoShouldNotBeFound("idUsuarioActualizacion.equals=" + UPDATED_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCContactosByIdUsuarioActualizacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where idUsuarioActualizacion not equals to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCContactoShouldNotBeFound("idUsuarioActualizacion.notEquals=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cContactoList where idUsuarioActualizacion not equals to UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCContactoShouldBeFound("idUsuarioActualizacion.notEquals=" + UPDATED_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCContactosByIdUsuarioActualizacionIsInShouldWork() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where idUsuarioActualizacion in DEFAULT_ID_USUARIO_ACTUALIZACION or UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCContactoShouldBeFound("idUsuarioActualizacion.in=" + DEFAULT_ID_USUARIO_ACTUALIZACION + "," + UPDATED_ID_USUARIO_ACTUALIZACION);

        // Get all the cContactoList where idUsuarioActualizacion equals to UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCContactoShouldNotBeFound("idUsuarioActualizacion.in=" + UPDATED_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCContactosByIdUsuarioActualizacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where idUsuarioActualizacion is not null
        defaultCContactoShouldBeFound("idUsuarioActualizacion.specified=true");

        // Get all the cContactoList where idUsuarioActualizacion is null
        defaultCContactoShouldNotBeFound("idUsuarioActualizacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCContactosByIdUsuarioActualizacionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where idUsuarioActualizacion is greater than or equal to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCContactoShouldBeFound("idUsuarioActualizacion.greaterThanOrEqual=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cContactoList where idUsuarioActualizacion is greater than or equal to (DEFAULT_ID_USUARIO_ACTUALIZACION + 1)
        defaultCContactoShouldNotBeFound("idUsuarioActualizacion.greaterThanOrEqual=" + (DEFAULT_ID_USUARIO_ACTUALIZACION + 1));
    }

    @Test
    @Transactional
    public void getAllCContactosByIdUsuarioActualizacionIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where idUsuarioActualizacion is less than or equal to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCContactoShouldBeFound("idUsuarioActualizacion.lessThanOrEqual=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cContactoList where idUsuarioActualizacion is less than or equal to SMALLER_ID_USUARIO_ACTUALIZACION
        defaultCContactoShouldNotBeFound("idUsuarioActualizacion.lessThanOrEqual=" + SMALLER_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCContactosByIdUsuarioActualizacionIsLessThanSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where idUsuarioActualizacion is less than DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCContactoShouldNotBeFound("idUsuarioActualizacion.lessThan=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cContactoList where idUsuarioActualizacion is less than (DEFAULT_ID_USUARIO_ACTUALIZACION + 1)
        defaultCContactoShouldBeFound("idUsuarioActualizacion.lessThan=" + (DEFAULT_ID_USUARIO_ACTUALIZACION + 1));
    }

    @Test
    @Transactional
    public void getAllCContactosByIdUsuarioActualizacionIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where idUsuarioActualizacion is greater than DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCContactoShouldNotBeFound("idUsuarioActualizacion.greaterThan=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cContactoList where idUsuarioActualizacion is greater than SMALLER_ID_USUARIO_ACTUALIZACION
        defaultCContactoShouldBeFound("idUsuarioActualizacion.greaterThan=" + SMALLER_ID_USUARIO_ACTUALIZACION);
    }


    @Test
    @Transactional
    public void getAllCContactosByFechaActualizacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where fechaActualizacion equals to DEFAULT_FECHA_ACTUALIZACION
        defaultCContactoShouldBeFound("fechaActualizacion.equals=" + DEFAULT_FECHA_ACTUALIZACION);

        // Get all the cContactoList where fechaActualizacion equals to UPDATED_FECHA_ACTUALIZACION
        defaultCContactoShouldNotBeFound("fechaActualizacion.equals=" + UPDATED_FECHA_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCContactosByFechaActualizacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where fechaActualizacion not equals to DEFAULT_FECHA_ACTUALIZACION
        defaultCContactoShouldNotBeFound("fechaActualizacion.notEquals=" + DEFAULT_FECHA_ACTUALIZACION);

        // Get all the cContactoList where fechaActualizacion not equals to UPDATED_FECHA_ACTUALIZACION
        defaultCContactoShouldBeFound("fechaActualizacion.notEquals=" + UPDATED_FECHA_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCContactosByFechaActualizacionIsInShouldWork() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where fechaActualizacion in DEFAULT_FECHA_ACTUALIZACION or UPDATED_FECHA_ACTUALIZACION
        defaultCContactoShouldBeFound("fechaActualizacion.in=" + DEFAULT_FECHA_ACTUALIZACION + "," + UPDATED_FECHA_ACTUALIZACION);

        // Get all the cContactoList where fechaActualizacion equals to UPDATED_FECHA_ACTUALIZACION
        defaultCContactoShouldNotBeFound("fechaActualizacion.in=" + UPDATED_FECHA_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCContactosByFechaActualizacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where fechaActualizacion is not null
        defaultCContactoShouldBeFound("fechaActualizacion.specified=true");

        // Get all the cContactoList where fechaActualizacion is null
        defaultCContactoShouldNotBeFound("fechaActualizacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCContactosByNotasIsEqualToSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where notas equals to DEFAULT_NOTAS
        defaultCContactoShouldBeFound("notas.equals=" + DEFAULT_NOTAS);

        // Get all the cContactoList where notas equals to UPDATED_NOTAS
        defaultCContactoShouldNotBeFound("notas.equals=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCContactosByNotasIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where notas not equals to DEFAULT_NOTAS
        defaultCContactoShouldNotBeFound("notas.notEquals=" + DEFAULT_NOTAS);

        // Get all the cContactoList where notas not equals to UPDATED_NOTAS
        defaultCContactoShouldBeFound("notas.notEquals=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCContactosByNotasIsInShouldWork() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where notas in DEFAULT_NOTAS or UPDATED_NOTAS
        defaultCContactoShouldBeFound("notas.in=" + DEFAULT_NOTAS + "," + UPDATED_NOTAS);

        // Get all the cContactoList where notas equals to UPDATED_NOTAS
        defaultCContactoShouldNotBeFound("notas.in=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCContactosByNotasIsNullOrNotNull() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where notas is not null
        defaultCContactoShouldBeFound("notas.specified=true");

        // Get all the cContactoList where notas is null
        defaultCContactoShouldNotBeFound("notas.specified=false");
    }
                @Test
    @Transactional
    public void getAllCContactosByNotasContainsSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where notas contains DEFAULT_NOTAS
        defaultCContactoShouldBeFound("notas.contains=" + DEFAULT_NOTAS);

        // Get all the cContactoList where notas contains UPDATED_NOTAS
        defaultCContactoShouldNotBeFound("notas.contains=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCContactosByNotasNotContainsSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where notas does not contain DEFAULT_NOTAS
        defaultCContactoShouldNotBeFound("notas.doesNotContain=" + DEFAULT_NOTAS);

        // Get all the cContactoList where notas does not contain UPDATED_NOTAS
        defaultCContactoShouldBeFound("notas.doesNotContain=" + UPDATED_NOTAS);
    }


    @Test
    @Transactional
    public void getAllCContactosByEstatusIsEqualToSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where estatus equals to DEFAULT_ESTATUS
        defaultCContactoShouldBeFound("estatus.equals=" + DEFAULT_ESTATUS);

        // Get all the cContactoList where estatus equals to UPDATED_ESTATUS
        defaultCContactoShouldNotBeFound("estatus.equals=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCContactosByEstatusIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where estatus not equals to DEFAULT_ESTATUS
        defaultCContactoShouldNotBeFound("estatus.notEquals=" + DEFAULT_ESTATUS);

        // Get all the cContactoList where estatus not equals to UPDATED_ESTATUS
        defaultCContactoShouldBeFound("estatus.notEquals=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCContactosByEstatusIsInShouldWork() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where estatus in DEFAULT_ESTATUS or UPDATED_ESTATUS
        defaultCContactoShouldBeFound("estatus.in=" + DEFAULT_ESTATUS + "," + UPDATED_ESTATUS);

        // Get all the cContactoList where estatus equals to UPDATED_ESTATUS
        defaultCContactoShouldNotBeFound("estatus.in=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCContactosByEstatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where estatus is not null
        defaultCContactoShouldBeFound("estatus.specified=true");

        // Get all the cContactoList where estatus is null
        defaultCContactoShouldNotBeFound("estatus.specified=false");
    }

    @Test
    @Transactional
    public void getAllCContactosByEstatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where estatus is greater than or equal to DEFAULT_ESTATUS
        defaultCContactoShouldBeFound("estatus.greaterThanOrEqual=" + DEFAULT_ESTATUS);

        // Get all the cContactoList where estatus is greater than or equal to (DEFAULT_ESTATUS + 1)
        defaultCContactoShouldNotBeFound("estatus.greaterThanOrEqual=" + (DEFAULT_ESTATUS + 1));
    }

    @Test
    @Transactional
    public void getAllCContactosByEstatusIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where estatus is less than or equal to DEFAULT_ESTATUS
        defaultCContactoShouldBeFound("estatus.lessThanOrEqual=" + DEFAULT_ESTATUS);

        // Get all the cContactoList where estatus is less than or equal to SMALLER_ESTATUS
        defaultCContactoShouldNotBeFound("estatus.lessThanOrEqual=" + SMALLER_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCContactosByEstatusIsLessThanSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where estatus is less than DEFAULT_ESTATUS
        defaultCContactoShouldNotBeFound("estatus.lessThan=" + DEFAULT_ESTATUS);

        // Get all the cContactoList where estatus is less than (DEFAULT_ESTATUS + 1)
        defaultCContactoShouldBeFound("estatus.lessThan=" + (DEFAULT_ESTATUS + 1));
    }

    @Test
    @Transactional
    public void getAllCContactosByEstatusIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where estatus is greater than DEFAULT_ESTATUS
        defaultCContactoShouldNotBeFound("estatus.greaterThan=" + DEFAULT_ESTATUS);

        // Get all the cContactoList where estatus is greater than SMALLER_ESTATUS
        defaultCContactoShouldBeFound("estatus.greaterThan=" + SMALLER_ESTATUS);
    }


    @Test
    @Transactional
    public void getAllCContactosByBorradoIsEqualToSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where borrado equals to DEFAULT_BORRADO
        defaultCContactoShouldBeFound("borrado.equals=" + DEFAULT_BORRADO);

        // Get all the cContactoList where borrado equals to UPDATED_BORRADO
        defaultCContactoShouldNotBeFound("borrado.equals=" + UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCContactosByBorradoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where borrado not equals to DEFAULT_BORRADO
        defaultCContactoShouldNotBeFound("borrado.notEquals=" + DEFAULT_BORRADO);

        // Get all the cContactoList where borrado not equals to UPDATED_BORRADO
        defaultCContactoShouldBeFound("borrado.notEquals=" + UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCContactosByBorradoIsInShouldWork() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where borrado in DEFAULT_BORRADO or UPDATED_BORRADO
        defaultCContactoShouldBeFound("borrado.in=" + DEFAULT_BORRADO + "," + UPDATED_BORRADO);

        // Get all the cContactoList where borrado equals to UPDATED_BORRADO
        defaultCContactoShouldNotBeFound("borrado.in=" + UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCContactosByBorradoIsNullOrNotNull() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where borrado is not null
        defaultCContactoShouldBeFound("borrado.specified=true");

        // Get all the cContactoList where borrado is null
        defaultCContactoShouldNotBeFound("borrado.specified=false");
    }

    @Test
    @Transactional
    public void getAllCContactosByBorradoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where borrado is greater than or equal to DEFAULT_BORRADO
        defaultCContactoShouldBeFound("borrado.greaterThanOrEqual=" + DEFAULT_BORRADO);

        // Get all the cContactoList where borrado is greater than or equal to (DEFAULT_BORRADO + 1)
        defaultCContactoShouldNotBeFound("borrado.greaterThanOrEqual=" + (DEFAULT_BORRADO + 1));
    }

    @Test
    @Transactional
    public void getAllCContactosByBorradoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where borrado is less than or equal to DEFAULT_BORRADO
        defaultCContactoShouldBeFound("borrado.lessThanOrEqual=" + DEFAULT_BORRADO);

        // Get all the cContactoList where borrado is less than or equal to SMALLER_BORRADO
        defaultCContactoShouldNotBeFound("borrado.lessThanOrEqual=" + SMALLER_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCContactosByBorradoIsLessThanSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where borrado is less than DEFAULT_BORRADO
        defaultCContactoShouldNotBeFound("borrado.lessThan=" + DEFAULT_BORRADO);

        // Get all the cContactoList where borrado is less than (DEFAULT_BORRADO + 1)
        defaultCContactoShouldBeFound("borrado.lessThan=" + (DEFAULT_BORRADO + 1));
    }

    @Test
    @Transactional
    public void getAllCContactosByBorradoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        // Get all the cContactoList where borrado is greater than DEFAULT_BORRADO
        defaultCContactoShouldNotBeFound("borrado.greaterThan=" + DEFAULT_BORRADO);

        // Get all the cContactoList where borrado is greater than SMALLER_BORRADO
        defaultCContactoShouldBeFound("borrado.greaterThan=" + SMALLER_BORRADO);
    }


    @Test
    @Transactional
    public void getAllCContactosByClienteIsEqualToSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);
        CCliente cliente = CClienteResourceIT.createEntity(em);
        em.persist(cliente);
        em.flush();
        cContacto.setCliente(cliente);
        cContactoRepository.saveAndFlush(cContacto);
        Long clienteId = cliente.getId();

        // Get all the cContactoList where cliente equals to clienteId
        defaultCContactoShouldBeFound("clienteId.equals=" + clienteId);

        // Get all the cContactoList where cliente equals to clienteId + 1
        defaultCContactoShouldNotBeFound("clienteId.equals=" + (clienteId + 1));
    }


    @Test
    @Transactional
    public void getAllCContactosByPasajeroIsEqualToSomething() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);
        CPasajero pasajero = CPasajeroResourceIT.createEntity(em);
        em.persist(pasajero);
        em.flush();
        cContacto.setPasajero(pasajero);
        cContactoRepository.saveAndFlush(cContacto);
        Long pasajeroId = pasajero.getId();

        // Get all the cContactoList where pasajero equals to pasajeroId
        defaultCContactoShouldBeFound("pasajeroId.equals=" + pasajeroId);

        // Get all the cContactoList where pasajero equals to pasajeroId + 1
        defaultCContactoShouldNotBeFound("pasajeroId.equals=" + (pasajeroId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCContactoShouldBeFound(String filter) throws Exception {
        restCContactoMockMvc.perform(get("/api/c-contactos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cContacto.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombreContacto").value(hasItem(DEFAULT_NOMBRE_CONTACTO)))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO)))
            .andExpect(jsonPath("$.[*].area").value(hasItem(DEFAULT_AREA)))
            .andExpect(jsonPath("$.[*].idUsuarioCreacion").value(hasItem(DEFAULT_ID_USUARIO_CREACION.intValue())))
            .andExpect(jsonPath("$.[*].fechaCreacion").value(hasItem(DEFAULT_FECHA_CREACION.toString())))
            .andExpect(jsonPath("$.[*].idUsuarioActualizacion").value(hasItem(DEFAULT_ID_USUARIO_ACTUALIZACION.intValue())))
            .andExpect(jsonPath("$.[*].fechaActualizacion").value(hasItem(DEFAULT_FECHA_ACTUALIZACION.toString())))
            .andExpect(jsonPath("$.[*].notas").value(hasItem(DEFAULT_NOTAS)))
            .andExpect(jsonPath("$.[*].estatus").value(hasItem(DEFAULT_ESTATUS)))
            .andExpect(jsonPath("$.[*].borrado").value(hasItem(DEFAULT_BORRADO)));

        // Check, that the count call also returns 1
        restCContactoMockMvc.perform(get("/api/c-contactos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCContactoShouldNotBeFound(String filter) throws Exception {
        restCContactoMockMvc.perform(get("/api/c-contactos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCContactoMockMvc.perform(get("/api/c-contactos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCContacto() throws Exception {
        // Get the cContacto
        restCContactoMockMvc.perform(get("/api/c-contactos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCContacto() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        int databaseSizeBeforeUpdate = cContactoRepository.findAll().size();

        // Update the cContacto
        CContacto updatedCContacto = cContactoRepository.findById(cContacto.getId()).get();
        // Disconnect from session so that the updates on updatedCContacto are not directly saved in db
        em.detach(updatedCContacto);
        updatedCContacto
            .nombreContacto(UPDATED_NOMBRE_CONTACTO)
            .telefono(UPDATED_TELEFONO)
            .area(UPDATED_AREA)
            .idUsuarioCreacion(UPDATED_ID_USUARIO_CREACION)
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .idUsuarioActualizacion(UPDATED_ID_USUARIO_ACTUALIZACION)
            .fechaActualizacion(UPDATED_FECHA_ACTUALIZACION)
            .notas(UPDATED_NOTAS)
            .estatus(UPDATED_ESTATUS)
            .borrado(UPDATED_BORRADO);
        CContactoDTO cContactoDTO = cContactoMapper.toDto(updatedCContacto);

        restCContactoMockMvc.perform(put("/api/c-contactos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cContactoDTO)))
            .andExpect(status().isOk());

        // Validate the CContacto in the database
        List<CContacto> cContactoList = cContactoRepository.findAll();
        assertThat(cContactoList).hasSize(databaseSizeBeforeUpdate);
        CContacto testCContacto = cContactoList.get(cContactoList.size() - 1);
        assertThat(testCContacto.getNombreContacto()).isEqualTo(UPDATED_NOMBRE_CONTACTO);
        assertThat(testCContacto.getTelefono()).isEqualTo(UPDATED_TELEFONO);
        assertThat(testCContacto.getArea()).isEqualTo(UPDATED_AREA);
        assertThat(testCContacto.getIdUsuarioCreacion()).isEqualTo(UPDATED_ID_USUARIO_CREACION);
        assertThat(testCContacto.getFechaCreacion()).isEqualTo(UPDATED_FECHA_CREACION);
        assertThat(testCContacto.getIdUsuarioActualizacion()).isEqualTo(UPDATED_ID_USUARIO_ACTUALIZACION);
        assertThat(testCContacto.getFechaActualizacion()).isEqualTo(UPDATED_FECHA_ACTUALIZACION);
        assertThat(testCContacto.getNotas()).isEqualTo(UPDATED_NOTAS);
        assertThat(testCContacto.getEstatus()).isEqualTo(UPDATED_ESTATUS);
        assertThat(testCContacto.getBorrado()).isEqualTo(UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void updateNonExistingCContacto() throws Exception {
        int databaseSizeBeforeUpdate = cContactoRepository.findAll().size();

        // Create the CContacto
        CContactoDTO cContactoDTO = cContactoMapper.toDto(cContacto);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCContactoMockMvc.perform(put("/api/c-contactos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cContactoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CContacto in the database
        List<CContacto> cContactoList = cContactoRepository.findAll();
        assertThat(cContactoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCContacto() throws Exception {
        // Initialize the database
        cContactoRepository.saveAndFlush(cContacto);

        int databaseSizeBeforeDelete = cContactoRepository.findAll().size();

        // Delete the cContacto
        restCContactoMockMvc.perform(delete("/api/c-contactos/{id}", cContacto.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CContacto> cContactoList = cContactoRepository.findAll();
        assertThat(cContactoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
