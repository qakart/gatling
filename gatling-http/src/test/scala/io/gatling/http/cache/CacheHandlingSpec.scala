/**
 * Copyright 2011-2013 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gatling.http.cache

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CacheHandlingSpec extends Specification {

	"convertExpireField" should {

		"supports Expires field format" in {
			CacheHandling.isFutureExpire("Thu, 01 Dec 1994 16:00:00 GMT") must beFalse
			CacheHandling.isFutureExpire("Tue, 19 Jan 2038 03:14:06 GMT") must beTrue
		}

		"supports Int format" in {
			CacheHandling.isFutureExpire("0") must beFalse
			CacheHandling.isFutureExpire(Int.MaxValue.toString) must beTrue
		}

		"defaults to false if it's not Expires field format nor Int format" in {
			CacheHandling.isFutureExpire("fail") must beFalse
		}
	}
}