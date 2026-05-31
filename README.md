# Commission Tracker Android

Aplicativo Android nativo desenvolvido em Kotlin para gerenciamento de comissões de serviços automotivos.

## Sobre o projeto

Este projeto foi criado para resolver uma necessidade real do ambiente de trabalho de um auto center, permitindo registrar, acompanhar e consultar comissões de serviços realizados.

O aplicativo possibilita o cadastro de serviços, controle de valores recebidos e acompanhamento das comissões ao longo do mês, facilitando o controle financeiro e a produtividade do profissional.

## Funcionalidades

* Cadastro de comissões
* Registro da data do serviço
* Registro do veículo atendido
* Seleção de serviços pré-cadastrados
* Cadastro de serviços personalizados
* Armazenamento local utilizando Room Database
* Listagem de comissões cadastradas
* Ordenação por data
* Navegação entre telas utilizando Navigation Component

## Tecnologias Utilizadas

* Kotlin
* Android SDK
* Fragments
* Navigation Component
* RecyclerView
* Room Database
* Coroutines
* ViewBinding
* Material Design Components

## Arquitetura

O projeto segue uma arquitetura em camadas para facilitar manutenção e evolução:

```text
UI
 ├─ Fragments
 ├─ RecyclerView
 └─ ViewBinding

Data
 ├─ Room Database
 ├─ DAO
 ├─ Entity
 └─ TypeConverters
```

## Estrutura do Projeto

```text
com.example.comisso
│
├── data
│   ├── dao
│   ├── entity
│   ├── converter
│   └── database
│
├── ui
│   ├── comissao
│   └── main
│
└── adapter
```

## Funcionalidades Planejadas

* Filtro por mês
* Totalização mensal das comissões
* CRUD completo de serviços
* Relatórios de desempenho
* Estatísticas de serviços realizados
* Exportação de dados

## Aprendizados

Durante o desenvolvimento deste projeto foram praticados conceitos como:

* Persistência de dados com Room
* Manipulação de datas utilizando LocalDate
* Conversores customizados para Room
* Navegação entre Fragments
* RecyclerView e Adapter
* Organização em camadas
* Boas práticas de desenvolvimento Android

## Como executar

1. Clone o repositório
2. Abra o projeto no Android Studio
3. Aguarde o download das dependências Gradle
4. Execute o aplicativo em um dispositivo físico ou emulador

## Autor

Ricardo Bove Filho

Desenvolvedor Android Kotlin
