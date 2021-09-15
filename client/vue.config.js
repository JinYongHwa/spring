module.exports = {
  lintOnSave: false,
    // 개발 서버 설정
    devServer: {
        proxy: "http://localhost:8080/board/"
    }
}
