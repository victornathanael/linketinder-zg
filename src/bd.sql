-- Tabela de Competências
CREATE TABLE competencias (
    id SERIAL PRIMARY KEY,
    nome VARCHAR UNIQUE NOT NULL
);

-- Tabela de Candidatos
CREATE TABLE candidatos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    idade INT NOT NULL,
    estado VARCHAR(2) NOT NULL,
    cep VARCHAR(10) NOT NULL,
    descricao TEXT NOT NULL
);

-- Tabela de Empresas
CREATE TABLE empresas (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    cnpj VARCHAR(18) UNIQUE NOT NULL,
    pais VARCHAR(60) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    cep VARCHAR(10) NOT NULL,
    descricao TEXT NOT NULL
);

-- Tabela de Vagas
CREATE TABLE vagas (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    descricao TEXT NOT NULL,
    empresa_id INT NOT NULL,
    FOREIGN KEY (empresa_id) REFERENCES empresas(id)
);

-- Relacionamento entre Candidatos e Vagas (Curtidas)
CREATE TABLE curtidas_dos_candidatos (
    candidato_id INT NOT NULL,
    vaga_id INT NOT NULL,
    PRIMARY KEY (candidato_id, vaga_id),
    FOREIGN KEY (candidato_id) REFERENCES candidatos(id),
    FOREIGN KEY (vaga_id) REFERENCES vagas(id)
);

-- Relacionamento entre Empresas e Candidatos (Curtidas)
CREATE TABLE curtidas_das_empresas (
     empresa_id INT NOT NULL,
     candidato_id INT NOT NULL,
     PRIMARY KEY (empresa_id, candidato_id),
     FOREIGN KEY (empresa_id) REFERENCES empresas(id),
     FOREIGN KEY (candidato_id) REFERENCES candidatos(id)
);

-- Relacionamento entre Candidatos e Competencias (Competencias)
CREATE TABLE competencias_candidatos (
     candidato_id INT NOT NULL,
     competencia_id INT NOT NULL,
     PRIMARY KEY (candidato_id, competencia_id),
     FOREIGN KEY (candidato_id) REFERENCES candidatos(id),
     FOREIGN KEY (competencia_id) REFERENCES competencias(id)
);

-- Relacionamento entre Empresas e Competencias (Competencias)
CREATE TABLE competencias_empresas (
    empresa_id INT NOT NULL,
    competencia_id INT NOT NULL,
    PRIMARY KEY (empresa_id, competencia_id),
    FOREIGN KEY (empresa_id) REFERENCES empresas(id),
    FOREIGN KEY (competencia_id) REFERENCES competencias(id)
);

-- Relacionamento entre Vagas e Competencias (Competencias)
CREATE TABLE competencias_vagas (
    vaga_id INT NOT NULL,
    competencia_id INT NOT NULL,
    PRIMARY KEY (vaga_id, competencia_id),
    FOREIGN KEY (vaga_id) REFERENCES vagas(id),
    FOREIGN KEY (competencia_id) REFERENCES competencias(id)
);

-- Inserções em Competências:
INSERT INTO competencias (nome) VALUES
    ('Java'), ('Python'), ('JavaScript'), ('React'), ('Angular');

-- Inserir candidatos
INSERT INTO candidatos (nome, email, cpf, idade, estado, cep, descricao) VALUES
    ('João Silva', 'joao@email.com', '123.456.789-01', 25, 'SP', '12345-678', 'Desenvolvedor Full Stack'),
    ('Maria Oliveira', 'maria@email.com', '987.654.321-01', 30, 'RJ', '98765-432', 'Engenheira de Dados'),
    ('Carlos Santos', 'carlos@email.com', '111.222.333-44', 28, 'MG', '56789-012', 'Analista de Sistemas');

-- Relacionamento Candidatos e Competências
INSERT INTO candidatos_competencias (candidato_id, competencia_id) VALUES
    (1, 1), (1, 2), (1, 3), (2, 2), (2, 4), (3, 3), (3, 5);

INSERT INTO empresas (nome, email, cnpj, pais, estado, cep, descricao) VALUES
    ('TechCorp', 'contato@techcorp.com', '12.345.678/0001-01', 'Brasil', 'SP', '12345-678', 'Desenvolvimento de software'),
    ('DataTech', 'contato@datatech.com', '98.765.432/0001-02', 'Brasil', 'RJ', '98765-432', 'Análise de dados'),
    ('InovaSoft', 'contato@inovasoft.com', '11.22.33/0001-03', 'Brasil', 'MG', '56789-012', 'Soluções inovadoras');

-- Relacionamento Empresas e Competências
INSERT INTO empresas_competencias (empresa_id, competencia_id) VALUES
    (1, 1), (1, 2), (2, 3), (3, 4), (3, 5);

INSERT INTO vagas (nome, descricao, empresa_id) VALUES
    ('Desenvolvedor Java', 'Desenvolvimento de aplicativos Java', 1),
    ('Cientista de Dados', 'Análise de dados e machine learning', 2),
    ('Engenheiro de Software', 'Desenvolvimento de software', 3);

-- Relacionamento Vagas e Competências
INSERT INTO vagas_competencias (vaga_id, competencia_id) VALUES
    (1, 1), (1, 2), (1, 3), (2, 2), (2, 4), (3, 3), (3, 5);

-- Consulta para obter todos os candidatos e suas competências:
SELECT c.nome as candidato, c.email, array_agg(co.nome) as competencias
FROM candidatos c
LEFT JOIN candidatos_competencias cc ON c.id = cc.candidato_id
LEFT JOIN competencias co ON cc.competencia_id = co.id
GROUP BY c.id;

-- Consulta para obter todas as empresas e suas competências:
SELECT e.nome as empresa, e.email, array_agg(co.nome) as competencias
FROM empresas e
LEFT JOIN empresas_competencias ec ON e.id = ec.empresa_id
LEFT JOIN competencias co ON ec.competencia_id = co.id
GROUP BY e.id;

-- Consulta para obter todas as vagas e suas competências:
SELECT v.nome as vaga, v.descricao, array_agg(co.nome) as competencias
FROM vagas v
LEFT JOIN vagas_competencias vc ON v.id = vc.vaga_id
LEFT JOIN competencias co ON vc.competencia_id = co.id
GROUP BY v.id;


-- Consulta para obter todas as competências associadas a uma vaga específica:
SELECT v.nome as vaga, array_agg(co.nome) as competencias
FROM vagas v
LEFT JOIN vagas_competencias vc ON v.id = vc.vaga_id
LEFT JOIN competencias co ON vc.competencia_id = co.id
WHERE v.id = 1
GROUP BY v.id;

-- Consulta para obter todas as competências de um candidato específico:
SELECT c.nome as candidato, array_agg(co.nome) as competencias
FROM candidatos c
LEFT JOIN candidatos_competencias cc ON c.id = cc.candidato_id
LEFT JOIN competencias co ON cc.competencia_id = co.id
WHERE c.id = 1
GROUP BY c.id;