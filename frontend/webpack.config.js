const path = require('path')

module.exports = {
    mode: 'production',
    entry: {
        candidateProfile: './src/pages/candidate-profile/candidate-profile.ts',
        candidateRegister: './src/pages/candidate-register/candidate-register.ts',
        companieRegister: './src/pages/companie-register/companie-register.ts',
        companieProfile: './src/pages/companie-profile/companie-profile.ts',
        createJob: './src/pages/companie-profile/create-job.ts',
    },
    output: {
        filename: '[name].min.js',
        path: path.join(__dirname, 'dist')
    },
    resolve: {
        extensions: ['.ts', '.js']
    },
    module: {
        rules: [{
            test: /\.ts$/,
            use: 'ts-loader',
            exclude: /node_modules/
        }]
    }
}
