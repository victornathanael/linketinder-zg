import candidate.Candidate
import company.Company
import util.ClearConsole
import util.Menu

class App {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in)
        while (true) {
            Menu.menu()
            def choice
            try {
                choice = scanner.nextInt()
            } catch (InputMismatchException e) {
                println "Digite um número válido"
                scanner.nextLine()
                ClearConsole.clearConsole()
                continue
            }

            switch (choice) {
                case 1 -> Candidate.listCandidates()
                case 2 -> Company.listCompanies()
                case 3 -> Candidate.newCandidate()
                case 4 -> Company.newCompany()
                case 5 -> {
                    ClearConsole.clearConsole()
                    println "Saindo..."
                }
                default -> {
                    ClearConsole.clearConsole()
                    println "Digite um número válido"
                }
            }
            if (choice == 5) {
                break;
            }
        }
    }
}
