CREATE TABLE usuario (
    id UUID PRIMARY KEY,  -- ID único para cada usuario, generado como UUID
    nombre VARCHAR(255) NOT NULL,  -- Nombre del usuario
    correo VARCHAR(255) NOT NULL UNIQUE,  -- Correo electrónico, debe ser único
    contraseña VARCHAR(255) NOT NULL,  -- Contraseña del usuario
    creado TIMESTAMP NOT NULL,  -- Fecha de creación del usuario
    modificado TIMESTAMP NOT NULL,  -- Fecha de última modificación del usuario
    ultimo_ingreso TIMESTAMP NOT NULL,  -- Fecha del último ingreso del usuario
    token VARCHAR(255) NOT NULL,  -- Token JWT para el usuario
    esta_activo BOOLEAN NOT NULL  -- Indica si el usuario está activo
);

CREATE TABLE telefono (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- ID único para cada teléfono, auto-incremental
    numero VARCHAR(20) NOT NULL,  -- Número de teléfono
    codigo_ciudad VARCHAR(10) NOT NULL,  -- Código de la ciudad
    codigo_pais VARCHAR(10) NOT NULL,  -- Código del país
    usuario_id UUID,  -- Clave foránea que referencia al usuario
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE  -- Relación con la tabla usuario
);

INSERT INTO usuario (id, nombre, correo, contraseña, creado, modificado, ultimo_ingreso, token, esta_activo)
VALUES
    ('4fd15c60-7e5d-42e3-9733-467e43adfcbd', 'Juan Rodriguez', 'juan@rodriguez.org', 'hunter2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'jwt_token_12345', TRUE),
    ('d9c558e6-4c58-4c2e-9234-6c8b6357d612', 'Maria Garcia', 'maria@garcia.com', 'claveSegura1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'jwt_token_67890', TRUE),
    ('3a7d6a3f-1f57-4f3f-84a9-8139c9bb6c2f', 'Carlos Perez', 'carlos@perez.net', 'claveSegura2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'jwt_token_54321', FALSE);

INSERT INTO telefono (id, numero, codigo_ciudad, codigo_pais, usuario_id)
VALUES
    (1, '1234567', '1', '57', '4fd15c60-7e5d-42e3-9733-467e43adfcbd'),
    (2, '7654321', '2', '57', '4fd15c60-7e5d-42e3-9733-467e43adfcbd'),
    (3, '9876543', '3', '34', 'd9c558e6-4c58-4c2e-9234-6c8b6357d612'),
    (4, '6543210', '4', '1', '3a7d6a3f-1f57-4f3f-84a9-8139c9bb6c2f');