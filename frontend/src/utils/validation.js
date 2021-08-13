function validateEmail(email) {
  var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
  return re.test(String(email).toLowerCase())
}

function UserIdValid(userID) {
  var expression = RegExp(/[^a-zA-Z0-9]/)
  return !expression.test(userID)
}
// 한글, 영어, 숫자 허용
function UsernameValid(userName) {
  var expression = RegExp(/[^가-힣a-zA-Z0-9]/)
  return !expression.test(userName)
}

function BlankValid(data) {
  var expression = /\s/
  return expression.test(data)
}

export { validateEmail, UserIdValid, UsernameValid, BlankValid }
