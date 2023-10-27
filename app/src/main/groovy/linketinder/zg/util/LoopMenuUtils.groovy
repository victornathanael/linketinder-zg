package linketinder.zg.util

import static linketinder.zg.util.ClearConsole.clearConsole

import static TextMenuUtils.menuPrincipal
import static TextMenuUtils.menuCandidate
import static TextMenuUtils.menuSkill
import static TextMenuUtils.menuCompany
import static TextMenuUtils.menuJob

import static linketinder.zg.view.Candidates.DeleteCandidate.deleteCandidate
import static linketinder.zg.view.Candidates.ListCandidates.listCandidates
import static linketinder.zg.view.Candidates.NewCandidate.newCandidate
import static linketinder.zg.view.Candidates.UpdateCandidate.updateCandidate

import static linketinder.zg.view.Companies.DeleteCompany.deleteCompany
import static linketinder.zg.view.Companies.ListCompanies.listCompanies
import static linketinder.zg.view.Companies.NewCompany.newCompany
import static linketinder.zg.view.Companies.UpdateCompany.updateCompany

import static linketinder.zg.view.Jobs.DeleteJob.deleteJob
import static linketinder.zg.view.Jobs.ListJobs.listJobs
import static linketinder.zg.view.Jobs.NewJob.newJob
import static linketinder.zg.view.Jobs.UpdateJob.updateJob

import static linketinder.zg.view.Skills.DeleteSkill.deleteSkill
import static linketinder.zg.view.Skills.ListSkills.listSkills
import static linketinder.zg.view.Skills.NewSkill.newSkill
import static linketinder.zg.view.Skills.UpdateSkill.updateSkill

class LoopMenuUtils {
    static void loopMenuPrincipal(Scanner scanner) {
        while (true) {
            menuPrincipal()
            switch (scanner.nextInt()) {
                case 1:
                    loopMenuCandidate(scanner)
                    break
                case 2:
                    loopMenuCompany(scanner)
                    break
                case 3:
                    loopMenuJob(scanner)
                    break
                case 4:
                    loopMenuSkill(scanner)
                    break
                case 5:
                    clearConsole()
                    println("Saindo...")
                    return
                default:
                    clearConsole()
                    println("Digite um número válido")
            }
        }
    }

    static void loopMenuCandidate(Scanner scanner) {
        while (true) {
            menuCandidate()
            switch (scanner.nextInt()) {
                case 1:
                    listCandidates()
                    break
                case 2:
                    newCandidate()
                    break
                case 3:
                    updateCandidate()
                    break
                case 4:
                    deleteCandidate()
                    break
                case 5:
                    return
                default:
                    println("Digite um número válido")
            }
        }
    }

    static void loopMenuCompany(Scanner scanner) {
        while (true) {
            menuCompany()
            switch (scanner.nextInt()) {
                case 1:
                    listCompanies()
                    break
                case 2:
                    newCompany()
                    break
                case 3:
                    updateCompany()
                    break
                case 4:
                    deleteCompany()
                    break
                case 5:
                    return
                default:
                    println("Digite um número válido")
            }
        }
    }

    static void loopMenuJob(Scanner scanner) {
        while (true) {
            menuJob()
            switch (scanner.nextInt()) {
                case 1:
                    listJobs()
                    break
                case 2:
                    newJob()
                    break
                case 3:
                    updateJob()
                    break
                case 4:
                    deleteJob()
                    break
                case 5:
                    return
                default:
                    println("Digite um número válido")
            }
        }
    }

    static void loopMenuSkill(Scanner scanner) {
        while (true) {
            menuSkill()
            switch (scanner.nextInt()) {
                case 1:
                    listSkills()
                    break
                case 2:
                    newSkill()
                    break
                case 3:
                    updateSkill()
                    break
                case 4:
                    deleteSkill()
                    break
                case 5:
                    return
                default:
                    println("Digite um número válido")
            }
        }
    }
}