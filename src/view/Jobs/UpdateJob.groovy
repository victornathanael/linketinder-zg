package view.Jobs

import model.Job.JobsDAO

class UpdateJob {
    static void updateJob() {
        ListJobs.listJobs()
        Scanner input = new Scanner(System.in)
        print("Informe o id da vaga: ");
        int id = input.nextInt();
        JobsDAO.update(id, input)
    }
}
