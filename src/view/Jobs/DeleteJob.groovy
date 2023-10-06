package view.Jobs

import model.Job.JobsDAO

class DeleteJob {
    static void deleteJob() {
        ListJobs.listJobs()
        Scanner input = new Scanner(System.in)
        print("Informe o id da vaga : ");
        int id = input.nextInt();
        JobsDAO.delete(id)
    }
}
