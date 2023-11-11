package linketinder.zg.model.Candidate

class Candidate {
    private int id
    private String name
    private String email
    private String cpf
    private int age
    private String state
    private String cep
    private String personalDescription
    private List<String> skills

    Candidate(String name, String email, String cpf, int age, String state, String cep, String personalDescription, List<String>  skills) {
        this.name = name
        this.email = email
        this.cpf = cpf
        this.age = age
        this.state = state
        this.cep = cep
        this.personalDescription = personalDescription
        this.skills = skills
    }

    Candidate(String name, String email, String cpf, int age, String state, String cep, String personalDescription) {
        this.name = name
        this.email = email
        this.cpf = cpf
        this.age = age
        this.state = state
        this.cep = cep
        this.personalDescription = personalDescription
    }

    int getId() {
        return id
    }

    void setId(int id) {
        this.id = id
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    String getEmail() {
        return email
    }

    void setEmail(String email) {
        this.email = email
    }

    String getCpf() {
        return cpf
    }

    void setCpf(String cpf) {
        this.cpf = cpf
    }

    int getAge() {
        return age
    }

    void setAge(int age) {
        this.age = age
    }

    String getState() {
        return state
    }

    void setState(String state) {
        this.state = state
    }

    String getCep() {
        return cep
    }

    void setCep(String cep) {
        this.cep = cep
    }

    String getPersonalDescription() {
        return personalDescription
    }

    void setPersonalDescription(String personalDescription) {
        this.personalDescription = personalDescription
    }

    List<String> getSkills() {
        return skills
    }

    void setSkills(List<String> skills) {
        this.skills = skills
    }

    @Override
    String toString() {
        return "Candidate{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", age=" + age +
                ", state='" + state + '\'' +
                ", cep='" + cep + '\'' +
                ", personalDescription='" + personalDescription + '\'' +
                ", skills=" + skills +
                '}';
    }
}


