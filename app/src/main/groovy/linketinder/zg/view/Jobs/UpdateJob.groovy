package linketinder.zg.view.Jobs

import linketinder.zg.model.Job.Job
import linketinder.zg.model.Job.JobsDAO
import linketinder.zg.util.InputUtils

class UpdateJob {
    static void updateJob() {
        ListJobs.listJobs()
        Scanner input = new Scanner(System.in)
        print("Informe o id da vaga: ");
        int id = input.nextInt();
        JobsDAO.update(id, input)
    }

    static Job inputsUpdateJob(int id) {
        String name = InputUtils.getStringInput("Digite o novo nome: ")
        String description = InputUtils.getStringInput("Digite a nova descrição: ")

        Job jobs = new Job(name, description, id)

        return jobs
    }
}
