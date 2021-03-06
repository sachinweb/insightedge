/*
 * Copyright (c) 2016, GigaSpaces Technologies, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.insightedge.spark.utils

import org.scalatest.Assertions._
import play.api.libs.json.{JsValue, Json}
import play.api.libs.ws.WSResponse
import play.api.libs.json._
import play.api.libs.functional.syntax._

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._


/**
  * @author Oleksiy_Dyagilev
  */
object RestUtils {

  def jsonBody(respFuture: Future[WSResponse], timeout: Duration = 1.second): JsValue = {
    val res = Await.result(respFuture, timeout)
    assert(res.status == 200, res)
    println(res.body)
    Json.parse(res.body)
  }
}
