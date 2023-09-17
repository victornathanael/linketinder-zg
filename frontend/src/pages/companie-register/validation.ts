export function validarNome(nome: string): boolean {
    const regexNome: RegExp = /^[A-Za-zÀ-ÖØ-öø-ÿ\s\-']+$/u

    return regexNome.test(nome)
}

export function validarEmail(email: string): boolean {
    const regexEmail: RegExp = /\S+@\w+\.\w{2,6}(\.\w{2})?/g

    return regexEmail.test(email)
}

export function validarCNPJ(cnpj: string): boolean {
    const regexCNPJ: RegExp = /^\d{2}\.\d{3}\.\d{3}\/\d{4}-\d{2}$|^\d{14}$/g

    return regexCNPJ.test(cnpj)
}

export function validarCEP(cep: string): boolean {
    const regexCEP: RegExp = /^\d{5}-?\d{3}$/g

    return regexCEP.test(cep)
}