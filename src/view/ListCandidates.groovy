package view

import model.CandidateDAO
import util.ClearConsole

class ListCandidates {
     static void listCandidates() {
        ClearConsole.clearConsole()
        CandidateDAO.candidates.each { candidate ->
            println "Name: ${candidate.name}"
            println "Email: ${candidate.email}"
            println "Competências: ${candidate.skills.join(', ')}\n"
        }
    }
}
