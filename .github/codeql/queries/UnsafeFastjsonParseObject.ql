/**
 * @name Unsafe fastjson JSON.parseObject() usage
 * @description Detects the use of fastjson's JSON.parseObject(), which can lead to deserialization vulnerabilities.
 * @kind problem
 * @problem.severity error
 * @id java/unsafe-fastjson-deserialization
 * @tags security
 *       external/cwe/cwe-502
 */

import java

from MethodAccess parseObjectCall
where
  parseObjectCall.getMethod().hasName("parseObject") and
  parseObjectCall.getMethod().getDeclaringType().hasQualifiedName("com.alibaba.fastjson", "JSON")
select parseObjectCall, "Unsafe deserialization using fastjson's JSON.parseObject()."