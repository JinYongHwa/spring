module.exports = {
  lintOnSave: false,
  // 개발 서버 설정
   devServer: {
        proxy: { // proxyTable 설정
            '/api': {
                target: 'http://localhost:8080/board/api',
                changeOrigin: true
            }
        }
    },
}
