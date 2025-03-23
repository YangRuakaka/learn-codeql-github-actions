// 示例代码：可能存在安全问题的代码
function getUserInput() {
  return document.getElementById('userInput').value;
}

function executeQuery() {
  const userInput = getUserInput();
  // 模拟 SQL 查询（可能存在 SQL 注入漏洞）
  const query = `SELECT * FROM users WHERE username = '${userInput}'`;
  console.log(query);
}

executeQuery();