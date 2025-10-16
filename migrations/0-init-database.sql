CREATE TABLE paciente (
    cpf VARCHAR(11) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    sexo VARCHAR(15) NOT NULL,
    PRIMARY KEY (cpf)
);

CREATE TABLE hemograma (
    id SERIAL,
    data_analise DATE,
    cpf_paciente VARCHAR(11),
    contagem_leucocitos INTEGER,
    neutrofilos DECIMAL(5, 2),
    linfocitos DECIMAL(5, 2),
    monocitos DECIMAL(5, 2),
    eosinofilos DECIMAL(5, 2),
    basofilos DECIMAL(5, 2),
    contagem_eritrocitos DECIMAL(5, 2),
    hemoglobina DECIMAL(5, 2),
    hematocrito DECIMAL(5, 2),
    volume_corpuscular_medio DECIMAL(5, 2),
    hemoglobina_corpuscular_media DECIMAL(5, 2),
    conc_hemoglobina_corpuscular_media DECIMAL(5, 2),
    amplitude_dist_eritrocitaria DECIMAL(5, 2),
    contagem_plaquetas INTEGER,
    volume_plaquetario_medio DECIMAL(5, 2),
    PRIMARY KEY (id),
    CONSTRAINT fk_hemograma_paciente FOREIGN KEY (cpf_paciente) REFERENCES paciente(cpf)
);

CREATE TABLE pontos_de_atencao (
    id SERIAL NOT NULL,
    descricao TEXT,
    hipotese TEXT,
    data_criacao DATE,
    cpf_paciente VARCHAR(11),
    PRIMARY KEY (id),
    CONSTRAINT fk_patencao_paciente FOREIGN KEY (cpf_paciente) REFERENCES paciente(cpf)
);

CREATE TABLE patencao_hemograma (
    id SERIAL NOT NULL,
    id_ponto_atencao INTEGER,
    id_hemograma INTEGER,
    PRIMARY KEY (id),
    CONSTRAINT fk_pah_patencao FOREIGN KEY (id_ponto_atencao) REFERENCES pontos_de_atencao(id),
    CONSTRAINT fk_pah_hemograma FOREIGN KEY (id_hemograma) REFERENCES hemograma(id)
);