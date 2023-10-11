package linketinder.zg

import linketinder.zg.util.*

import linketinder.zg.view.Candidates.*
import linketinder.zg.view.Companies.*
import linketinder.zg.view.Jobs.*
import linketinder.zg.view.Skills.*

class App {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in)

        loopMenuPrincipal(scanner)
    }

    static void loopMenuPrincipal(Scanner scanner) {
        while (true) {
            Menu.menuPrincipal()

            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException ignored) {
                println("Digite um número válido")
                scanner.nextLine()
                ClearConsole.clearConsole()
                continue
            }

            switch (choice) {
                case 1:
                    loopMenuCandidato(scanner)
                    break
                case 2:
                    loopMenuEmpresa(scanner)
                    break
                case 3:
                    loopMenuVagas(scanner)
                    break
                case 4:
                    loopMenuCompetencias(scanner)
                    break
                case 5:
                    ClearConsole.clearConsole()
                    println("Saindo...")
                    return
                default:
                    ClearConsole.clearConsole()
                    println("Digite um número válido")
            }
        }
    }

    static void loopMenuCandidato(Scanner scanner) {
        while (true) {
            Menu.menuCandidato()

            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException ignored) {
                println("Digite um número válido")
                scanner.nextLine()
                ClearConsole.clearConsole()
                continue
            }

            switch (choice) {
                case 1:
                    ListCandidates.listCandidates()
                    break
                case 2:
                    NewCandidate.newCandidate()
                    break
                case 3:
                    UpdateCandidate.updateCandidate()
                    break
                case 4:
                    DeleteCandidate.deleteCandidate()
                    break
                case 5:
                    return
                default:
                    println("Digite um número válido")
            }
        }
    }

    static void loopMenuEmpresa(Scanner scanner) {
        while (true) {
            Menu.menuEmpresa()

            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException ignored) {
                println("Digite um número válido")
                scanner.nextLine()
                ClearConsole.clearConsole()
                continue;
            }

            switch (choice) {
                case 1:
                    ListCompanies.listCompanies()
                    break
                case 2:
                    NewCompany.newCompany()
                    break
                case 3:
                    UpdateCompany.updateCompany()
                    break
                case 4:
                    DeleteCompany.deleteCompany()
                    break
                case 5:
                    return
                default:
                    println("Digite um número válido")
            }
        }
    }

    static void loopMenuVagas(Scanner scanner) {
        while (true) {
            Menu.menuVaga()

            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException ignored) {
                println("Digite um número válido")
                scanner.nextLine()
                ClearConsole.clearConsole()
                continue
            }

            switch (choice) {
                case 1:
                    ListJobs.listJobs()
                    break
                case 2:
                    NewJob.newJob()
                    break
                case 3:
                    UpdateJob.updateJob()
                    break
                case 4:
                    DeleteJob.deleteJob()
                    break
                case 5:
                    return
                default:
                    println("Digite um número válido")
            }
        }
    }

    static void loopMenuCompetencias(Scanner scanner) {
        while (true) {
            Menu.menuCompetencia()

            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException ignored) {
                println("Digite um número válido")
                scanner.nextLine()
                ClearConsole.clearConsole()
                continue
            }

            switch (choice) {
                case 1:
                    ListSkills.listSkills()
                    break
                case 2:
                    NewSkill.newSkill()
                    break
                case 3:
                    UpdateSkill.updateSkill()
                    break
                case 4:
                    DeleteSkill.deleteSkill()
                    break
                case 5:
                    return
                default:
                    println("Digite um número válido")
            }
        }
    }
}
