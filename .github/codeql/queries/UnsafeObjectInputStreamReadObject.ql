/**
 * @name Unsafe ObjectInputStream.readObject() usage
 * @description Detects the use of ObjectInputStream.readObject(), which can lead to insecure deserialization.
 * @kind problem
 * @problem.severity error
 * @id java/unsafe-deserialization
 * @tags security
 *       external/cwe/cwe-502
 */

import java

from MethodAccess readObjectCall
where
  readObjectCall.getMethod().hasName("readObject") and
  readObjectCall.getMethod().getDeclaringType().hasQualifiedName("java.io", "ObjectInputStream")
select readObjectCall, "Unsafe deserialization using ObjectInputStream.readObject()."