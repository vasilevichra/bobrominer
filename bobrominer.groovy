#!/usr/bin/env groovy

@Grab(group='com.codeborne', module='selenide', version='5.5.0')
import static com.codeborne.selenide.Selenide.*
import static com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Selectors
import static com.codeborne.selenide.Selectors.byText
import com.codeborne.selenide.Configuration
import java.util.Random

Configuration.clickViaJs = false
Configuration.browser = "firefox"
Configuration.headless = true

open("https://jive.croc.ru/login.jspa?referer=%2Fcommunity%2Fknowledge%2Feveryday%2Fperson%2Fguess%2F")
$("#sso-login-submit").click()
sleep(7000)

times = 100 + new Random().nextInt(200)

for (i in 1..times) {

  timeout = 2000 + new Random().nextInt(2000)

  $$("#jive-widget-guess-colleague-question a").get(new Random().nextInt(3)).click()
  $(".loadersmall").should(disappear)
  sleep(1000)

  $(byText("Следующий вопрос")).click()
  $(".loadersmall").should(disappear)
  sleep(timeout)

}
