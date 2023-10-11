package linketinder.zg.view.Jobs


import linketinder.zg.model.Job.Jobs
import linketinder.zg.model.Job.JobsDAO
import linketinder.zg.view.Companies.ListCompanies

class NewJob {
    static void newJob() {
        Scanner input = new Scanner(System.in)

        print "Digite o nome da nova vaga: "
        String name = input.nextLine()

        print "Digite a descrição da nova vaga: "
        String description = input.nextLine()

        ListCompanies.listCompanies()

        print "Digite o id da empresa responsável por essa vaga: "
        int idEmpresa = input.nextInt()


        Jobs jobs = new Jobs(name, description, idEmpresa)
        JobsDAO.create(jobs)
    }
}
