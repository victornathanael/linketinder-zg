export function validarNome(nome: string): boolean {
    const regexNome: RegExp = /^[A-Za-zÀ-ÖØ-öø-ÿ\s\-']+$/u

    return regexNome.test(nome)
}

export function validarEmail(email: string): boolean {
    const regexEmail: RegExp = /\S+@\w+\.\w{2,6}(\.\w{2})?/g

    return regexEmail.test(email)
}

export function validarCPF(cpf: string): boolean {
    const regexCPF: RegExp = /^(\d{3}\.){2}\d{3}-\d{2}$|^\d{11}$/g

    return regexCPF.test(cpf)
}

export function validarIdade(idade: string): boolean {
    const regexIdade: RegExp = /^(1[6-9]|[2-9][0-9]|100)$/g

    return regexIdade.test(idade)
}

export function validarCEP(cep: string): boolean {
    const regexCEP: RegExp = /^\d{5}-?\d{3}$/g

    return regexCEP.test(cep)
}