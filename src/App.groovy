import util.ClearConsole
import util.Menu
import view.ListCandidates
import view.ListCompanies
import view.NewCandidate
import view.NewCompany

class App {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in)
        while (true) {

            Menu.menu()

            def choice
            try {
                choice = scanner.nextInt()
            } catch (InputMismatchException ignored) {
                println "Digite um número válido"
                scanner.nextLine()
                ClearConsole.clearConsole()
                continue
            }

            switch (choice) {
                case 1 -> ListCandidates.listCandidates()
                case 2 -> ListCompanies.listCompanies()
                case 3 -> NewCandidate.newCandidate()
                case 4 -> NewCompany.newCompany()
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
