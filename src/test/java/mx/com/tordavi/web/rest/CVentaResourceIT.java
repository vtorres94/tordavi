package mx.com.tordavi.web.rest;

import mx.com.tordavi.TordaviApp;
import mx.com.tordavi.domain.CVenta;
import mx.com.tordavi.domain.CCliente;
import mx.com.tordavi.domain.CReservacion;
import mx.com.tordavi.repository.CVentaRepository;
import mx.com.tordavi.service.CVentaService;
import mx.com.tordavi.service.dto.CVentaDTO;
import mx.com.tordavi.service.mapper.CVentaMapper;
import mx.com.tordavi.service.dto.CVentaCriteria;
import mx.com.tordavi.service.CVentaQueryService;

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
 * Integration tests for the {@link CVentaResource} REST controller.
 */
@SpringBootTest(classes = TordaviApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class CVentaResourceIT {

    private static final Long DEFAULT_VENDEDOR_ID = 999999999999L;
    private static final Long UPDATED_VENDEDOR_ID = 999999999998L;
    private static final Long SMALLER_VENDEDOR_ID = 999999999999L - 1L;

    private static final Double DEFAULT_PRECIO_TOTAL = 1D;
    private static final Double UPDATED_PRECIO_TOTAL = 2D;
    private static final Double SMALLER_PRECIO_TOTAL = 1D - 1D;

    private static final Instant DEFAULT_FECHA_VENTA = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_FECHA_VENTA = Instant.now().truncatedTo(ChronoUnit.MILLIS);

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
    private CVentaRepository cVentaRepository;

    @Autowired
    private CVentaMapper cVentaMapper;

    @Autowired
    private CVentaService cVentaService;

    @Autowired
    private CVentaQueryService cVentaQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCVentaMockMvc;

    private CVenta cVenta;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CVenta createEntity(EntityManager em) {
        CVenta cVenta = new CVenta()
            .vendedorId(DEFAULT_VENDEDOR_ID)
            .precioTotal(DEFAULT_PRECIO_TOTAL)
            .fechaVenta(DEFAULT_FECHA_VENTA)
            .idUsuarioCreacion(DEFAULT_ID_USUARIO_CREACION)
            .fechaCreacion(DEFAULT_FECHA_CREACION)
            .idUsuarioActualizacion(DEFAULT_ID_USUARIO_ACTUALIZACION)
            .fechaActualizacion(DEFAULT_FECHA_ACTUALIZACION)
            .notas(DEFAULT_NOTAS)
            .estatus(DEFAULT_ESTATUS)
            .borrado(DEFAULT_BORRADO);
        return cVenta;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CVenta createUpdatedEntity(EntityManager em) {
        CVenta cVenta = new CVenta()
            .vendedorId(UPDATED_VENDEDOR_ID)
            .precioTotal(UPDATED_PRECIO_TOTAL)
            .fechaVenta(UPDATED_FECHA_VENTA)
            .idUsuarioCreacion(UPDATED_ID_USUARIO_CREACION)
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .idUsuarioActualizacion(UPDATED_ID_USUARIO_ACTUALIZACION)
            .fechaActualizacion(UPDATED_FECHA_ACTUALIZACION)
            .notas(UPDATED_NOTAS)
            .estatus(UPDATED_ESTATUS)
            .borrado(UPDATED_BORRADO);
        return cVenta;
    }

    @BeforeEach
    public void initTest() {
        cVenta = createEntity(em);
    }

    @Test
    @Transactional
    public void createCVenta() throws Exception {
        int databaseSizeBeforeCreate = cVentaRepository.findAll().size();

        // Create the CVenta
        CVentaDTO cVentaDTO = cVentaMapper.toDto(cVenta);
        restCVentaMockMvc.perform(post("/api/c-ventas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cVentaDTO)))
            .andExpect(status().isCreated());

        // Validate the CVenta in the database
        List<CVenta> cVentaList = cVentaRepository.findAll();
        assertThat(cVentaList).hasSize(databaseSizeBeforeCreate + 1);
        CVenta testCVenta = cVentaList.get(cVentaList.size() - 1);
        assertThat(testCVenta.getVendedorId()).isEqualTo(DEFAULT_VENDEDOR_ID);
        assertThat(testCVenta.getPrecioTotal()).isEqualTo(DEFAULT_PRECIO_TOTAL);
        assertThat(testCVenta.getFechaVenta()).isEqualTo(DEFAULT_FECHA_VENTA);
        assertThat(testCVenta.getIdUsuarioCreacion()).isEqualTo(DEFAULT_ID_USUARIO_CREACION);
        assertThat(testCVenta.getFechaCreacion()).isEqualTo(DEFAULT_FECHA_CREACION);
        assertThat(testCVenta.getIdUsuarioActualizacion()).isEqualTo(DEFAULT_ID_USUARIO_ACTUALIZACION);
        assertThat(testCVenta.getFechaActualizacion()).isEqualTo(DEFAULT_FECHA_ACTUALIZACION);
        assertThat(testCVenta.getNotas()).isEqualTo(DEFAULT_NOTAS);
        assertThat(testCVenta.getEstatus()).isEqualTo(DEFAULT_ESTATUS);
        assertThat(testCVenta.getBorrado()).isEqualTo(DEFAULT_BORRADO);
    }

    @Test
    @Transactional
    public void createCVentaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cVentaRepository.findAll().size();

        // Create the CVenta with an existing ID
        cVenta.setId(1L);
        CVentaDTO cVentaDTO = cVentaMapper.toDto(cVenta);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCVentaMockMvc.perform(post("/api/c-ventas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cVentaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CVenta in the database
        List<CVenta> cVentaList = cVentaRepository.findAll();
        assertThat(cVentaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkVendedorIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = cVentaRepository.findAll().size();
        // set the field null
        cVenta.setVendedorId(null);

        // Create the CVenta, which fails.
        CVentaDTO cVentaDTO = cVentaMapper.toDto(cVenta);

        restCVentaMockMvc.perform(post("/api/c-ventas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cVentaDTO)))
            .andExpect(status().isBadRequest());

        List<CVenta> cVentaList = cVentaRepository.findAll();
        assertThat(cVentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFechaVentaIsRequired() throws Exception {
        int databaseSizeBeforeTest = cVentaRepository.findAll().size();
        // set the field null
        cVenta.setFechaVenta(null);

        // Create the CVenta, which fails.
        CVentaDTO cVentaDTO = cVentaMapper.toDto(cVenta);

        restCVentaMockMvc.perform(post("/api/c-ventas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cVentaDTO)))
            .andExpect(status().isBadRequest());

        List<CVenta> cVentaList = cVentaRepository.findAll();
        assertThat(cVentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdUsuarioCreacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = cVentaRepository.findAll().size();
        // set the field null
        cVenta.setIdUsuarioCreacion(null);

        // Create the CVenta, which fails.
        CVentaDTO cVentaDTO = cVentaMapper.toDto(cVenta);

        restCVentaMockMvc.perform(post("/api/c-ventas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cVentaDTO)))
            .andExpect(status().isBadRequest());

        List<CVenta> cVentaList = cVentaRepository.findAll();
        assertThat(cVentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFechaCreacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = cVentaRepository.findAll().size();
        // set the field null
        cVenta.setFechaCreacion(null);

        // Create the CVenta, which fails.
        CVentaDTO cVentaDTO = cVentaMapper.toDto(cVenta);

        restCVentaMockMvc.perform(post("/api/c-ventas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cVentaDTO)))
            .andExpect(status().isBadRequest());

        List<CVenta> cVentaList = cVentaRepository.findAll();
        assertThat(cVentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = cVentaRepository.findAll().size();
        // set the field null
        cVenta.setEstatus(null);

        // Create the CVenta, which fails.
        CVentaDTO cVentaDTO = cVentaMapper.toDto(cVenta);

        restCVentaMockMvc.perform(post("/api/c-ventas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cVentaDTO)))
            .andExpect(status().isBadRequest());

        List<CVenta> cVentaList = cVentaRepository.findAll();
        assertThat(cVentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBorradoIsRequired() throws Exception {
        int databaseSizeBeforeTest = cVentaRepository.findAll().size();
        // set the field null
        cVenta.setBorrado(null);

        // Create the CVenta, which fails.
        CVentaDTO cVentaDTO = cVentaMapper.toDto(cVenta);

        restCVentaMockMvc.perform(post("/api/c-ventas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cVentaDTO)))
            .andExpect(status().isBadRequest());

        List<CVenta> cVentaList = cVentaRepository.findAll();
        assertThat(cVentaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCVentas() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList
        restCVentaMockMvc.perform(get("/api/c-ventas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cVenta.getId().intValue())))
            .andExpect(jsonPath("$.[*].vendedorId").value(hasItem(DEFAULT_VENDEDOR_ID.intValue())))
            .andExpect(jsonPath("$.[*].precioTotal").value(hasItem(DEFAULT_PRECIO_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].fechaVenta").value(hasItem(DEFAULT_FECHA_VENTA.toString())))
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
    public void getCVenta() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get the cVenta
        restCVentaMockMvc.perform(get("/api/c-ventas/{id}", cVenta.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cVenta.getId().intValue()))
            .andExpect(jsonPath("$.vendedorId").value(DEFAULT_VENDEDOR_ID.intValue()))
            .andExpect(jsonPath("$.precioTotal").value(DEFAULT_PRECIO_TOTAL.doubleValue()))
            .andExpect(jsonPath("$.fechaVenta").value(DEFAULT_FECHA_VENTA.toString()))
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
    public void getCVentasByIdFiltering() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        Long id = cVenta.getId();

        defaultCVentaShouldBeFound("id.equals=" + id);
        defaultCVentaShouldNotBeFound("id.notEquals=" + id);

        defaultCVentaShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCVentaShouldNotBeFound("id.greaterThan=" + id);

        defaultCVentaShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCVentaShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllCVentasByVendedorIdIsEqualToSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where vendedorId equals to DEFAULT_VENDEDOR_ID
        defaultCVentaShouldBeFound("vendedorId.equals=" + DEFAULT_VENDEDOR_ID);

        // Get all the cVentaList where vendedorId equals to UPDATED_VENDEDOR_ID
        defaultCVentaShouldNotBeFound("vendedorId.equals=" + UPDATED_VENDEDOR_ID);
    }

    @Test
    @Transactional
    public void getAllCVentasByVendedorIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where vendedorId not equals to DEFAULT_VENDEDOR_ID
        defaultCVentaShouldNotBeFound("vendedorId.notEquals=" + DEFAULT_VENDEDOR_ID);

        // Get all the cVentaList where vendedorId not equals to UPDATED_VENDEDOR_ID
        defaultCVentaShouldBeFound("vendedorId.notEquals=" + UPDATED_VENDEDOR_ID);
    }

    @Test
    @Transactional
    public void getAllCVentasByVendedorIdIsInShouldWork() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where vendedorId in DEFAULT_VENDEDOR_ID or UPDATED_VENDEDOR_ID
        defaultCVentaShouldBeFound("vendedorId.in=" + DEFAULT_VENDEDOR_ID + "," + UPDATED_VENDEDOR_ID);

        // Get all the cVentaList where vendedorId equals to UPDATED_VENDEDOR_ID
        defaultCVentaShouldNotBeFound("vendedorId.in=" + UPDATED_VENDEDOR_ID);
    }

    @Test
    @Transactional
    public void getAllCVentasByVendedorIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where vendedorId is not null
        defaultCVentaShouldBeFound("vendedorId.specified=true");

        // Get all the cVentaList where vendedorId is null
        defaultCVentaShouldNotBeFound("vendedorId.specified=false");
    }

    @Test
    @Transactional
    public void getAllCVentasByVendedorIdIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where vendedorId is greater than or equal to DEFAULT_VENDEDOR_ID
        defaultCVentaShouldBeFound("vendedorId.greaterThanOrEqual=" + DEFAULT_VENDEDOR_ID);

        // Get all the cVentaList where vendedorId is greater than or equal to (DEFAULT_VENDEDOR_ID + 1)
        defaultCVentaShouldNotBeFound("vendedorId.greaterThanOrEqual=" + (DEFAULT_VENDEDOR_ID + 1));
    }

    @Test
    @Transactional
    public void getAllCVentasByVendedorIdIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where vendedorId is less than or equal to DEFAULT_VENDEDOR_ID
        defaultCVentaShouldBeFound("vendedorId.lessThanOrEqual=" + DEFAULT_VENDEDOR_ID);

        // Get all the cVentaList where vendedorId is less than or equal to SMALLER_VENDEDOR_ID
        defaultCVentaShouldNotBeFound("vendedorId.lessThanOrEqual=" + SMALLER_VENDEDOR_ID);
    }

    @Test
    @Transactional
    public void getAllCVentasByVendedorIdIsLessThanSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where vendedorId is less than DEFAULT_VENDEDOR_ID
        defaultCVentaShouldNotBeFound("vendedorId.lessThan=" + DEFAULT_VENDEDOR_ID);

        // Get all the cVentaList where vendedorId is less than (DEFAULT_VENDEDOR_ID + 1)
        defaultCVentaShouldBeFound("vendedorId.lessThan=" + (DEFAULT_VENDEDOR_ID + 1));
    }

    @Test
    @Transactional
    public void getAllCVentasByVendedorIdIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where vendedorId is greater than DEFAULT_VENDEDOR_ID
        defaultCVentaShouldNotBeFound("vendedorId.greaterThan=" + DEFAULT_VENDEDOR_ID);

        // Get all the cVentaList where vendedorId is greater than SMALLER_VENDEDOR_ID
        defaultCVentaShouldBeFound("vendedorId.greaterThan=" + SMALLER_VENDEDOR_ID);
    }


    @Test
    @Transactional
    public void getAllCVentasByPrecioTotalIsEqualToSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where precioTotal equals to DEFAULT_PRECIO_TOTAL
        defaultCVentaShouldBeFound("precioTotal.equals=" + DEFAULT_PRECIO_TOTAL);

        // Get all the cVentaList where precioTotal equals to UPDATED_PRECIO_TOTAL
        defaultCVentaShouldNotBeFound("precioTotal.equals=" + UPDATED_PRECIO_TOTAL);
    }

    @Test
    @Transactional
    public void getAllCVentasByPrecioTotalIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where precioTotal not equals to DEFAULT_PRECIO_TOTAL
        defaultCVentaShouldNotBeFound("precioTotal.notEquals=" + DEFAULT_PRECIO_TOTAL);

        // Get all the cVentaList where precioTotal not equals to UPDATED_PRECIO_TOTAL
        defaultCVentaShouldBeFound("precioTotal.notEquals=" + UPDATED_PRECIO_TOTAL);
    }

    @Test
    @Transactional
    public void getAllCVentasByPrecioTotalIsInShouldWork() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where precioTotal in DEFAULT_PRECIO_TOTAL or UPDATED_PRECIO_TOTAL
        defaultCVentaShouldBeFound("precioTotal.in=" + DEFAULT_PRECIO_TOTAL + "," + UPDATED_PRECIO_TOTAL);

        // Get all the cVentaList where precioTotal equals to UPDATED_PRECIO_TOTAL
        defaultCVentaShouldNotBeFound("precioTotal.in=" + UPDATED_PRECIO_TOTAL);
    }

    @Test
    @Transactional
    public void getAllCVentasByPrecioTotalIsNullOrNotNull() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where precioTotal is not null
        defaultCVentaShouldBeFound("precioTotal.specified=true");

        // Get all the cVentaList where precioTotal is null
        defaultCVentaShouldNotBeFound("precioTotal.specified=false");
    }

    @Test
    @Transactional
    public void getAllCVentasByPrecioTotalIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where precioTotal is greater than or equal to DEFAULT_PRECIO_TOTAL
        defaultCVentaShouldBeFound("precioTotal.greaterThanOrEqual=" + DEFAULT_PRECIO_TOTAL);

        // Get all the cVentaList where precioTotal is greater than or equal to UPDATED_PRECIO_TOTAL
        defaultCVentaShouldNotBeFound("precioTotal.greaterThanOrEqual=" + UPDATED_PRECIO_TOTAL);
    }

    @Test
    @Transactional
    public void getAllCVentasByPrecioTotalIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where precioTotal is less than or equal to DEFAULT_PRECIO_TOTAL
        defaultCVentaShouldBeFound("precioTotal.lessThanOrEqual=" + DEFAULT_PRECIO_TOTAL);

        // Get all the cVentaList where precioTotal is less than or equal to SMALLER_PRECIO_TOTAL
        defaultCVentaShouldNotBeFound("precioTotal.lessThanOrEqual=" + SMALLER_PRECIO_TOTAL);
    }

    @Test
    @Transactional
    public void getAllCVentasByPrecioTotalIsLessThanSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where precioTotal is less than DEFAULT_PRECIO_TOTAL
        defaultCVentaShouldNotBeFound("precioTotal.lessThan=" + DEFAULT_PRECIO_TOTAL);

        // Get all the cVentaList where precioTotal is less than UPDATED_PRECIO_TOTAL
        defaultCVentaShouldBeFound("precioTotal.lessThan=" + UPDATED_PRECIO_TOTAL);
    }

    @Test
    @Transactional
    public void getAllCVentasByPrecioTotalIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where precioTotal is greater than DEFAULT_PRECIO_TOTAL
        defaultCVentaShouldNotBeFound("precioTotal.greaterThan=" + DEFAULT_PRECIO_TOTAL);

        // Get all the cVentaList where precioTotal is greater than SMALLER_PRECIO_TOTAL
        defaultCVentaShouldBeFound("precioTotal.greaterThan=" + SMALLER_PRECIO_TOTAL);
    }


    @Test
    @Transactional
    public void getAllCVentasByFechaVentaIsEqualToSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where fechaVenta equals to DEFAULT_FECHA_VENTA
        defaultCVentaShouldBeFound("fechaVenta.equals=" + DEFAULT_FECHA_VENTA);

        // Get all the cVentaList where fechaVenta equals to UPDATED_FECHA_VENTA
        defaultCVentaShouldNotBeFound("fechaVenta.equals=" + UPDATED_FECHA_VENTA);
    }

    @Test
    @Transactional
    public void getAllCVentasByFechaVentaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where fechaVenta not equals to DEFAULT_FECHA_VENTA
        defaultCVentaShouldNotBeFound("fechaVenta.notEquals=" + DEFAULT_FECHA_VENTA);

        // Get all the cVentaList where fechaVenta not equals to UPDATED_FECHA_VENTA
        defaultCVentaShouldBeFound("fechaVenta.notEquals=" + UPDATED_FECHA_VENTA);
    }

    @Test
    @Transactional
    public void getAllCVentasByFechaVentaIsInShouldWork() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where fechaVenta in DEFAULT_FECHA_VENTA or UPDATED_FECHA_VENTA
        defaultCVentaShouldBeFound("fechaVenta.in=" + DEFAULT_FECHA_VENTA + "," + UPDATED_FECHA_VENTA);

        // Get all the cVentaList where fechaVenta equals to UPDATED_FECHA_VENTA
        defaultCVentaShouldNotBeFound("fechaVenta.in=" + UPDATED_FECHA_VENTA);
    }

    @Test
    @Transactional
    public void getAllCVentasByFechaVentaIsNullOrNotNull() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where fechaVenta is not null
        defaultCVentaShouldBeFound("fechaVenta.specified=true");

        // Get all the cVentaList where fechaVenta is null
        defaultCVentaShouldNotBeFound("fechaVenta.specified=false");
    }

    @Test
    @Transactional
    public void getAllCVentasByIdUsuarioCreacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where idUsuarioCreacion equals to DEFAULT_ID_USUARIO_CREACION
        defaultCVentaShouldBeFound("idUsuarioCreacion.equals=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cVentaList where idUsuarioCreacion equals to UPDATED_ID_USUARIO_CREACION
        defaultCVentaShouldNotBeFound("idUsuarioCreacion.equals=" + UPDATED_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCVentasByIdUsuarioCreacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where idUsuarioCreacion not equals to DEFAULT_ID_USUARIO_CREACION
        defaultCVentaShouldNotBeFound("idUsuarioCreacion.notEquals=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cVentaList where idUsuarioCreacion not equals to UPDATED_ID_USUARIO_CREACION
        defaultCVentaShouldBeFound("idUsuarioCreacion.notEquals=" + UPDATED_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCVentasByIdUsuarioCreacionIsInShouldWork() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where idUsuarioCreacion in DEFAULT_ID_USUARIO_CREACION or UPDATED_ID_USUARIO_CREACION
        defaultCVentaShouldBeFound("idUsuarioCreacion.in=" + DEFAULT_ID_USUARIO_CREACION + "," + UPDATED_ID_USUARIO_CREACION);

        // Get all the cVentaList where idUsuarioCreacion equals to UPDATED_ID_USUARIO_CREACION
        defaultCVentaShouldNotBeFound("idUsuarioCreacion.in=" + UPDATED_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCVentasByIdUsuarioCreacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where idUsuarioCreacion is not null
        defaultCVentaShouldBeFound("idUsuarioCreacion.specified=true");

        // Get all the cVentaList where idUsuarioCreacion is null
        defaultCVentaShouldNotBeFound("idUsuarioCreacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCVentasByIdUsuarioCreacionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where idUsuarioCreacion is greater than or equal to DEFAULT_ID_USUARIO_CREACION
        defaultCVentaShouldBeFound("idUsuarioCreacion.greaterThanOrEqual=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cVentaList where idUsuarioCreacion is greater than or equal to (DEFAULT_ID_USUARIO_CREACION + 1)
        defaultCVentaShouldNotBeFound("idUsuarioCreacion.greaterThanOrEqual=" + (DEFAULT_ID_USUARIO_CREACION + 1));
    }

    @Test
    @Transactional
    public void getAllCVentasByIdUsuarioCreacionIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where idUsuarioCreacion is less than or equal to DEFAULT_ID_USUARIO_CREACION
        defaultCVentaShouldBeFound("idUsuarioCreacion.lessThanOrEqual=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cVentaList where idUsuarioCreacion is less than or equal to SMALLER_ID_USUARIO_CREACION
        defaultCVentaShouldNotBeFound("idUsuarioCreacion.lessThanOrEqual=" + SMALLER_ID_USUARIO_CREACION);
    }

    @Test
    @Transactional
    public void getAllCVentasByIdUsuarioCreacionIsLessThanSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where idUsuarioCreacion is less than DEFAULT_ID_USUARIO_CREACION
        defaultCVentaShouldNotBeFound("idUsuarioCreacion.lessThan=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cVentaList where idUsuarioCreacion is less than (DEFAULT_ID_USUARIO_CREACION + 1)
        defaultCVentaShouldBeFound("idUsuarioCreacion.lessThan=" + (DEFAULT_ID_USUARIO_CREACION + 1));
    }

    @Test
    @Transactional
    public void getAllCVentasByIdUsuarioCreacionIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where idUsuarioCreacion is greater than DEFAULT_ID_USUARIO_CREACION
        defaultCVentaShouldNotBeFound("idUsuarioCreacion.greaterThan=" + DEFAULT_ID_USUARIO_CREACION);

        // Get all the cVentaList where idUsuarioCreacion is greater than SMALLER_ID_USUARIO_CREACION
        defaultCVentaShouldBeFound("idUsuarioCreacion.greaterThan=" + SMALLER_ID_USUARIO_CREACION);
    }


    @Test
    @Transactional
    public void getAllCVentasByFechaCreacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where fechaCreacion equals to DEFAULT_FECHA_CREACION
        defaultCVentaShouldBeFound("fechaCreacion.equals=" + DEFAULT_FECHA_CREACION);

        // Get all the cVentaList where fechaCreacion equals to UPDATED_FECHA_CREACION
        defaultCVentaShouldNotBeFound("fechaCreacion.equals=" + UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void getAllCVentasByFechaCreacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where fechaCreacion not equals to DEFAULT_FECHA_CREACION
        defaultCVentaShouldNotBeFound("fechaCreacion.notEquals=" + DEFAULT_FECHA_CREACION);

        // Get all the cVentaList where fechaCreacion not equals to UPDATED_FECHA_CREACION
        defaultCVentaShouldBeFound("fechaCreacion.notEquals=" + UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void getAllCVentasByFechaCreacionIsInShouldWork() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where fechaCreacion in DEFAULT_FECHA_CREACION or UPDATED_FECHA_CREACION
        defaultCVentaShouldBeFound("fechaCreacion.in=" + DEFAULT_FECHA_CREACION + "," + UPDATED_FECHA_CREACION);

        // Get all the cVentaList where fechaCreacion equals to UPDATED_FECHA_CREACION
        defaultCVentaShouldNotBeFound("fechaCreacion.in=" + UPDATED_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void getAllCVentasByFechaCreacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where fechaCreacion is not null
        defaultCVentaShouldBeFound("fechaCreacion.specified=true");

        // Get all the cVentaList where fechaCreacion is null
        defaultCVentaShouldNotBeFound("fechaCreacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCVentasByIdUsuarioActualizacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where idUsuarioActualizacion equals to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCVentaShouldBeFound("idUsuarioActualizacion.equals=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cVentaList where idUsuarioActualizacion equals to UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCVentaShouldNotBeFound("idUsuarioActualizacion.equals=" + UPDATED_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCVentasByIdUsuarioActualizacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where idUsuarioActualizacion not equals to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCVentaShouldNotBeFound("idUsuarioActualizacion.notEquals=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cVentaList where idUsuarioActualizacion not equals to UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCVentaShouldBeFound("idUsuarioActualizacion.notEquals=" + UPDATED_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCVentasByIdUsuarioActualizacionIsInShouldWork() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where idUsuarioActualizacion in DEFAULT_ID_USUARIO_ACTUALIZACION or UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCVentaShouldBeFound("idUsuarioActualizacion.in=" + DEFAULT_ID_USUARIO_ACTUALIZACION + "," + UPDATED_ID_USUARIO_ACTUALIZACION);

        // Get all the cVentaList where idUsuarioActualizacion equals to UPDATED_ID_USUARIO_ACTUALIZACION
        defaultCVentaShouldNotBeFound("idUsuarioActualizacion.in=" + UPDATED_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCVentasByIdUsuarioActualizacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where idUsuarioActualizacion is not null
        defaultCVentaShouldBeFound("idUsuarioActualizacion.specified=true");

        // Get all the cVentaList where idUsuarioActualizacion is null
        defaultCVentaShouldNotBeFound("idUsuarioActualizacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCVentasByIdUsuarioActualizacionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where idUsuarioActualizacion is greater than or equal to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCVentaShouldBeFound("idUsuarioActualizacion.greaterThanOrEqual=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cVentaList where idUsuarioActualizacion is greater than or equal to (DEFAULT_ID_USUARIO_ACTUALIZACION + 1)
        defaultCVentaShouldNotBeFound("idUsuarioActualizacion.greaterThanOrEqual=" + (DEFAULT_ID_USUARIO_ACTUALIZACION + 1));
    }

    @Test
    @Transactional
    public void getAllCVentasByIdUsuarioActualizacionIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where idUsuarioActualizacion is less than or equal to DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCVentaShouldBeFound("idUsuarioActualizacion.lessThanOrEqual=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cVentaList where idUsuarioActualizacion is less than or equal to SMALLER_ID_USUARIO_ACTUALIZACION
        defaultCVentaShouldNotBeFound("idUsuarioActualizacion.lessThanOrEqual=" + SMALLER_ID_USUARIO_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCVentasByIdUsuarioActualizacionIsLessThanSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where idUsuarioActualizacion is less than DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCVentaShouldNotBeFound("idUsuarioActualizacion.lessThan=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cVentaList where idUsuarioActualizacion is less than (DEFAULT_ID_USUARIO_ACTUALIZACION + 1)
        defaultCVentaShouldBeFound("idUsuarioActualizacion.lessThan=" + (DEFAULT_ID_USUARIO_ACTUALIZACION + 1));
    }

    @Test
    @Transactional
    public void getAllCVentasByIdUsuarioActualizacionIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where idUsuarioActualizacion is greater than DEFAULT_ID_USUARIO_ACTUALIZACION
        defaultCVentaShouldNotBeFound("idUsuarioActualizacion.greaterThan=" + DEFAULT_ID_USUARIO_ACTUALIZACION);

        // Get all the cVentaList where idUsuarioActualizacion is greater than SMALLER_ID_USUARIO_ACTUALIZACION
        defaultCVentaShouldBeFound("idUsuarioActualizacion.greaterThan=" + SMALLER_ID_USUARIO_ACTUALIZACION);
    }


    @Test
    @Transactional
    public void getAllCVentasByFechaActualizacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where fechaActualizacion equals to DEFAULT_FECHA_ACTUALIZACION
        defaultCVentaShouldBeFound("fechaActualizacion.equals=" + DEFAULT_FECHA_ACTUALIZACION);

        // Get all the cVentaList where fechaActualizacion equals to UPDATED_FECHA_ACTUALIZACION
        defaultCVentaShouldNotBeFound("fechaActualizacion.equals=" + UPDATED_FECHA_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCVentasByFechaActualizacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where fechaActualizacion not equals to DEFAULT_FECHA_ACTUALIZACION
        defaultCVentaShouldNotBeFound("fechaActualizacion.notEquals=" + DEFAULT_FECHA_ACTUALIZACION);

        // Get all the cVentaList where fechaActualizacion not equals to UPDATED_FECHA_ACTUALIZACION
        defaultCVentaShouldBeFound("fechaActualizacion.notEquals=" + UPDATED_FECHA_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCVentasByFechaActualizacionIsInShouldWork() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where fechaActualizacion in DEFAULT_FECHA_ACTUALIZACION or UPDATED_FECHA_ACTUALIZACION
        defaultCVentaShouldBeFound("fechaActualizacion.in=" + DEFAULT_FECHA_ACTUALIZACION + "," + UPDATED_FECHA_ACTUALIZACION);

        // Get all the cVentaList where fechaActualizacion equals to UPDATED_FECHA_ACTUALIZACION
        defaultCVentaShouldNotBeFound("fechaActualizacion.in=" + UPDATED_FECHA_ACTUALIZACION);
    }

    @Test
    @Transactional
    public void getAllCVentasByFechaActualizacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where fechaActualizacion is not null
        defaultCVentaShouldBeFound("fechaActualizacion.specified=true");

        // Get all the cVentaList where fechaActualizacion is null
        defaultCVentaShouldNotBeFound("fechaActualizacion.specified=false");
    }

    @Test
    @Transactional
    public void getAllCVentasByNotasIsEqualToSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where notas equals to DEFAULT_NOTAS
        defaultCVentaShouldBeFound("notas.equals=" + DEFAULT_NOTAS);

        // Get all the cVentaList where notas equals to UPDATED_NOTAS
        defaultCVentaShouldNotBeFound("notas.equals=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCVentasByNotasIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where notas not equals to DEFAULT_NOTAS
        defaultCVentaShouldNotBeFound("notas.notEquals=" + DEFAULT_NOTAS);

        // Get all the cVentaList where notas not equals to UPDATED_NOTAS
        defaultCVentaShouldBeFound("notas.notEquals=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCVentasByNotasIsInShouldWork() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where notas in DEFAULT_NOTAS or UPDATED_NOTAS
        defaultCVentaShouldBeFound("notas.in=" + DEFAULT_NOTAS + "," + UPDATED_NOTAS);

        // Get all the cVentaList where notas equals to UPDATED_NOTAS
        defaultCVentaShouldNotBeFound("notas.in=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCVentasByNotasIsNullOrNotNull() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where notas is not null
        defaultCVentaShouldBeFound("notas.specified=true");

        // Get all the cVentaList where notas is null
        defaultCVentaShouldNotBeFound("notas.specified=false");
    }
                @Test
    @Transactional
    public void getAllCVentasByNotasContainsSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where notas contains DEFAULT_NOTAS
        defaultCVentaShouldBeFound("notas.contains=" + DEFAULT_NOTAS);

        // Get all the cVentaList where notas contains UPDATED_NOTAS
        defaultCVentaShouldNotBeFound("notas.contains=" + UPDATED_NOTAS);
    }

    @Test
    @Transactional
    public void getAllCVentasByNotasNotContainsSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where notas does not contain DEFAULT_NOTAS
        defaultCVentaShouldNotBeFound("notas.doesNotContain=" + DEFAULT_NOTAS);

        // Get all the cVentaList where notas does not contain UPDATED_NOTAS
        defaultCVentaShouldBeFound("notas.doesNotContain=" + UPDATED_NOTAS);
    }


    @Test
    @Transactional
    public void getAllCVentasByEstatusIsEqualToSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where estatus equals to DEFAULT_ESTATUS
        defaultCVentaShouldBeFound("estatus.equals=" + DEFAULT_ESTATUS);

        // Get all the cVentaList where estatus equals to UPDATED_ESTATUS
        defaultCVentaShouldNotBeFound("estatus.equals=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCVentasByEstatusIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where estatus not equals to DEFAULT_ESTATUS
        defaultCVentaShouldNotBeFound("estatus.notEquals=" + DEFAULT_ESTATUS);

        // Get all the cVentaList where estatus not equals to UPDATED_ESTATUS
        defaultCVentaShouldBeFound("estatus.notEquals=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCVentasByEstatusIsInShouldWork() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where estatus in DEFAULT_ESTATUS or UPDATED_ESTATUS
        defaultCVentaShouldBeFound("estatus.in=" + DEFAULT_ESTATUS + "," + UPDATED_ESTATUS);

        // Get all the cVentaList where estatus equals to UPDATED_ESTATUS
        defaultCVentaShouldNotBeFound("estatus.in=" + UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCVentasByEstatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where estatus is not null
        defaultCVentaShouldBeFound("estatus.specified=true");

        // Get all the cVentaList where estatus is null
        defaultCVentaShouldNotBeFound("estatus.specified=false");
    }

    @Test
    @Transactional
    public void getAllCVentasByEstatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where estatus is greater than or equal to DEFAULT_ESTATUS
        defaultCVentaShouldBeFound("estatus.greaterThanOrEqual=" + DEFAULT_ESTATUS);

        // Get all the cVentaList where estatus is greater than or equal to (DEFAULT_ESTATUS + 1)
        defaultCVentaShouldNotBeFound("estatus.greaterThanOrEqual=" + (DEFAULT_ESTATUS + 1));
    }

    @Test
    @Transactional
    public void getAllCVentasByEstatusIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where estatus is less than or equal to DEFAULT_ESTATUS
        defaultCVentaShouldBeFound("estatus.lessThanOrEqual=" + DEFAULT_ESTATUS);

        // Get all the cVentaList where estatus is less than or equal to SMALLER_ESTATUS
        defaultCVentaShouldNotBeFound("estatus.lessThanOrEqual=" + SMALLER_ESTATUS);
    }

    @Test
    @Transactional
    public void getAllCVentasByEstatusIsLessThanSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where estatus is less than DEFAULT_ESTATUS
        defaultCVentaShouldNotBeFound("estatus.lessThan=" + DEFAULT_ESTATUS);

        // Get all the cVentaList where estatus is less than (DEFAULT_ESTATUS + 1)
        defaultCVentaShouldBeFound("estatus.lessThan=" + (DEFAULT_ESTATUS + 1));
    }

    @Test
    @Transactional
    public void getAllCVentasByEstatusIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where estatus is greater than DEFAULT_ESTATUS
        defaultCVentaShouldNotBeFound("estatus.greaterThan=" + DEFAULT_ESTATUS);

        // Get all the cVentaList where estatus is greater than SMALLER_ESTATUS
        defaultCVentaShouldBeFound("estatus.greaterThan=" + SMALLER_ESTATUS);
    }


    @Test
    @Transactional
    public void getAllCVentasByBorradoIsEqualToSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where borrado equals to DEFAULT_BORRADO
        defaultCVentaShouldBeFound("borrado.equals=" + DEFAULT_BORRADO);

        // Get all the cVentaList where borrado equals to UPDATED_BORRADO
        defaultCVentaShouldNotBeFound("borrado.equals=" + UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCVentasByBorradoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where borrado not equals to DEFAULT_BORRADO
        defaultCVentaShouldNotBeFound("borrado.notEquals=" + DEFAULT_BORRADO);

        // Get all the cVentaList where borrado not equals to UPDATED_BORRADO
        defaultCVentaShouldBeFound("borrado.notEquals=" + UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCVentasByBorradoIsInShouldWork() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where borrado in DEFAULT_BORRADO or UPDATED_BORRADO
        defaultCVentaShouldBeFound("borrado.in=" + DEFAULT_BORRADO + "," + UPDATED_BORRADO);

        // Get all the cVentaList where borrado equals to UPDATED_BORRADO
        defaultCVentaShouldNotBeFound("borrado.in=" + UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCVentasByBorradoIsNullOrNotNull() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where borrado is not null
        defaultCVentaShouldBeFound("borrado.specified=true");

        // Get all the cVentaList where borrado is null
        defaultCVentaShouldNotBeFound("borrado.specified=false");
    }

    @Test
    @Transactional
    public void getAllCVentasByBorradoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where borrado is greater than or equal to DEFAULT_BORRADO
        defaultCVentaShouldBeFound("borrado.greaterThanOrEqual=" + DEFAULT_BORRADO);

        // Get all the cVentaList where borrado is greater than or equal to (DEFAULT_BORRADO + 1)
        defaultCVentaShouldNotBeFound("borrado.greaterThanOrEqual=" + (DEFAULT_BORRADO + 1));
    }

    @Test
    @Transactional
    public void getAllCVentasByBorradoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where borrado is less than or equal to DEFAULT_BORRADO
        defaultCVentaShouldBeFound("borrado.lessThanOrEqual=" + DEFAULT_BORRADO);

        // Get all the cVentaList where borrado is less than or equal to SMALLER_BORRADO
        defaultCVentaShouldNotBeFound("borrado.lessThanOrEqual=" + SMALLER_BORRADO);
    }

    @Test
    @Transactional
    public void getAllCVentasByBorradoIsLessThanSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where borrado is less than DEFAULT_BORRADO
        defaultCVentaShouldNotBeFound("borrado.lessThan=" + DEFAULT_BORRADO);

        // Get all the cVentaList where borrado is less than (DEFAULT_BORRADO + 1)
        defaultCVentaShouldBeFound("borrado.lessThan=" + (DEFAULT_BORRADO + 1));
    }

    @Test
    @Transactional
    public void getAllCVentasByBorradoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        // Get all the cVentaList where borrado is greater than DEFAULT_BORRADO
        defaultCVentaShouldNotBeFound("borrado.greaterThan=" + DEFAULT_BORRADO);

        // Get all the cVentaList where borrado is greater than SMALLER_BORRADO
        defaultCVentaShouldBeFound("borrado.greaterThan=" + SMALLER_BORRADO);
    }


    @Test
    @Transactional
    public void getAllCVentasByClienteIsEqualToSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);
        CCliente cliente = CClienteResourceIT.createEntity(em);
        em.persist(cliente);
        em.flush();
        cVenta.setCliente(cliente);
        cVentaRepository.saveAndFlush(cVenta);
        Long clienteId = cliente.getId();

        // Get all the cVentaList where cliente equals to clienteId
        defaultCVentaShouldBeFound("clienteId.equals=" + clienteId);

        // Get all the cVentaList where cliente equals to clienteId + 1
        defaultCVentaShouldNotBeFound("clienteId.equals=" + (clienteId + 1));
    }


    @Test
    @Transactional
    public void getAllCVentasByReservacionIsEqualToSomething() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);
        CReservacion reservacion = CReservacionResourceIT.createEntity(em);
        em.persist(reservacion);
        em.flush();
        cVenta.setReservacion(reservacion);
        cVentaRepository.saveAndFlush(cVenta);
        Long reservacionId = reservacion.getId();

        // Get all the cVentaList where reservacion equals to reservacionId
        defaultCVentaShouldBeFound("reservacionId.equals=" + reservacionId);

        // Get all the cVentaList where reservacion equals to reservacionId + 1
        defaultCVentaShouldNotBeFound("reservacionId.equals=" + (reservacionId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCVentaShouldBeFound(String filter) throws Exception {
        restCVentaMockMvc.perform(get("/api/c-ventas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cVenta.getId().intValue())))
            .andExpect(jsonPath("$.[*].vendedorId").value(hasItem(DEFAULT_VENDEDOR_ID.intValue())))
            .andExpect(jsonPath("$.[*].precioTotal").value(hasItem(DEFAULT_PRECIO_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].fechaVenta").value(hasItem(DEFAULT_FECHA_VENTA.toString())))
            .andExpect(jsonPath("$.[*].idUsuarioCreacion").value(hasItem(DEFAULT_ID_USUARIO_CREACION.intValue())))
            .andExpect(jsonPath("$.[*].fechaCreacion").value(hasItem(DEFAULT_FECHA_CREACION.toString())))
            .andExpect(jsonPath("$.[*].idUsuarioActualizacion").value(hasItem(DEFAULT_ID_USUARIO_ACTUALIZACION.intValue())))
            .andExpect(jsonPath("$.[*].fechaActualizacion").value(hasItem(DEFAULT_FECHA_ACTUALIZACION.toString())))
            .andExpect(jsonPath("$.[*].notas").value(hasItem(DEFAULT_NOTAS)))
            .andExpect(jsonPath("$.[*].estatus").value(hasItem(DEFAULT_ESTATUS)))
            .andExpect(jsonPath("$.[*].borrado").value(hasItem(DEFAULT_BORRADO)));

        // Check, that the count call also returns 1
        restCVentaMockMvc.perform(get("/api/c-ventas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCVentaShouldNotBeFound(String filter) throws Exception {
        restCVentaMockMvc.perform(get("/api/c-ventas?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCVentaMockMvc.perform(get("/api/c-ventas/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCVenta() throws Exception {
        // Get the cVenta
        restCVentaMockMvc.perform(get("/api/c-ventas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCVenta() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        int databaseSizeBeforeUpdate = cVentaRepository.findAll().size();

        // Update the cVenta
        CVenta updatedCVenta = cVentaRepository.findById(cVenta.getId()).get();
        // Disconnect from session so that the updates on updatedCVenta are not directly saved in db
        em.detach(updatedCVenta);
        updatedCVenta
            .vendedorId(UPDATED_VENDEDOR_ID)
            .precioTotal(UPDATED_PRECIO_TOTAL)
            .fechaVenta(UPDATED_FECHA_VENTA)
            .idUsuarioCreacion(UPDATED_ID_USUARIO_CREACION)
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .idUsuarioActualizacion(UPDATED_ID_USUARIO_ACTUALIZACION)
            .fechaActualizacion(UPDATED_FECHA_ACTUALIZACION)
            .notas(UPDATED_NOTAS)
            .estatus(UPDATED_ESTATUS)
            .borrado(UPDATED_BORRADO);
        CVentaDTO cVentaDTO = cVentaMapper.toDto(updatedCVenta);

        restCVentaMockMvc.perform(put("/api/c-ventas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cVentaDTO)))
            .andExpect(status().isOk());

        // Validate the CVenta in the database
        List<CVenta> cVentaList = cVentaRepository.findAll();
        assertThat(cVentaList).hasSize(databaseSizeBeforeUpdate);
        CVenta testCVenta = cVentaList.get(cVentaList.size() - 1);
        assertThat(testCVenta.getVendedorId()).isEqualTo(UPDATED_VENDEDOR_ID);
        assertThat(testCVenta.getPrecioTotal()).isEqualTo(UPDATED_PRECIO_TOTAL);
        assertThat(testCVenta.getFechaVenta()).isEqualTo(UPDATED_FECHA_VENTA);
        assertThat(testCVenta.getIdUsuarioCreacion()).isEqualTo(UPDATED_ID_USUARIO_CREACION);
        assertThat(testCVenta.getFechaCreacion()).isEqualTo(UPDATED_FECHA_CREACION);
        assertThat(testCVenta.getIdUsuarioActualizacion()).isEqualTo(UPDATED_ID_USUARIO_ACTUALIZACION);
        assertThat(testCVenta.getFechaActualizacion()).isEqualTo(UPDATED_FECHA_ACTUALIZACION);
        assertThat(testCVenta.getNotas()).isEqualTo(UPDATED_NOTAS);
        assertThat(testCVenta.getEstatus()).isEqualTo(UPDATED_ESTATUS);
        assertThat(testCVenta.getBorrado()).isEqualTo(UPDATED_BORRADO);
    }

    @Test
    @Transactional
    public void updateNonExistingCVenta() throws Exception {
        int databaseSizeBeforeUpdate = cVentaRepository.findAll().size();

        // Create the CVenta
        CVentaDTO cVentaDTO = cVentaMapper.toDto(cVenta);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCVentaMockMvc.perform(put("/api/c-ventas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(cVentaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CVenta in the database
        List<CVenta> cVentaList = cVentaRepository.findAll();
        assertThat(cVentaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCVenta() throws Exception {
        // Initialize the database
        cVentaRepository.saveAndFlush(cVenta);

        int databaseSizeBeforeDelete = cVentaRepository.findAll().size();

        // Delete the cVenta
        restCVentaMockMvc.perform(delete("/api/c-ventas/{id}", cVenta.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CVenta> cVentaList = cVentaRepository.findAll();
        assertThat(cVentaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
