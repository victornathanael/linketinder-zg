package model

class CompanyDAO {
    static List<Company> companies = [
            new Company(
                    name: "Google",
                    corporateEmail: "contact@google.com",
                    cnpj: "12.345.678/0001-23",
                    country: "Estados Unidos",
                    state: "Califórnia",
                    cep: "12345-678",
                    companyDescription: "A Google LLC é uma empresa multinacional de tecnologia que se especializa em serviços e produtos relacionados à Internet.",
                    skills: ["JavaScript", "Java", "Angular", "Python", "C#"]
            ),
            new Company(
                    name: "Apple",
                    corporateEmail: "info@apple.com",
                    cnpj: "98.765.432/0001-21",
                    country: "Estados Unidos",
                    state: "Califórnia",
                    cep: "98765-432",
                    companyDescription: "A Apple Inc. é uma empresa multinacional de tecnologia americana que projeta, desenvolve e vende eletrônicos de consumo, software e serviços online.",
                    skills: ["HTML", "CSS", "React", "Node.js", "Django"]

            ),
            new Company(
                    name: "Amazon",
                    corporateEmail: "contact@amazon.com",
                    cnpj: "55.555.555/0001-01",
                    country: "Estados Unidos",
                    state: "Washington",
                    cep: "54321-987",
                    companyDescription: "A Amazon.com, Inc. é uma empresa multinacional de tecnologia americana que se concentra em comércio eletrônico, computação em nuvem, streaming digital e inteligência artificial.",
                    skills: ["Bootstrap", "Vue.js", "SQL", "Ruby on Rails", "Express.js"]
            ),
            new Company(
                    name: "Microsoft",
                    corporateEmail: "info@microsoft.com",
                    cnpj: "11.222.333/0001-44",
                    country: "Estados Unidos",
                    state: "Washington",
                    cep: "12345-678",
                    companyDescription: "A Microsoft Corporation é uma empresa multinacional de tecnologia americana que desenvolve, licencia e vende software de computador, eletrônicos de consumo e computadores pessoais.",
                    skills: ["Git", "Jenkins", "Docker", "Ansible", "PostgreSQL"]
            ),
            new Company(
                    name: "Facebook",
                    corporateEmail: "contact@facebook.com",
                    cnpj: "66.777.888/0001-55",
                    country: "Estados Unidos",
                    state: "Califórnia",
                    cep: "45678-901",
                    companyDescription: "A Meta Platforms, Inc. (anteriormente conhecida como Facebook, Inc.) é um conglomerado de tecnologia americano especializado em mídia social e serviços relacionados.",
                    skills: ["MongoDB", "Redis", "Spring Boot", "HTML", "CSS"]
            )
    ]

    static void saveCompany(String name, String corporateEmail, String cnpj, String country, String state, String cep, String companyDescription, String skills) {
        companies.add(new Company(
                name: name,
                corporateEmail: corporateEmail,
                cnpj: cnpj,
                country: country,
                state: state,
                cep: cep,
                companyDescription: companyDescription,
                skills: [skills]
        ))
    }

}
