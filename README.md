## Hubspot Functional tests

https://github.com/francislainy/hubt/tree/master/src

`mvn spring-boot:run`

## Prior to starting

![Github code challenge](https://user-images.githubusercontent.com/19685849/136158873-e2a4fb95-05d8-47da-b60b-e2e66124068a.jpeg)

## Resulting curl:

```aidl

 curl --request POST 'https://candidate.hubteam.com/candidateTest/v3/problem/result?userKey=c967dc4a03fce49243388aef13f7' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json; charset=UTF-8' \
--data-raw '{"countries":[{"attendeeCount":15,"attendees":["jdapintox40hubspotpartners.com","mmacayanx40hubspotpartners.com","sdulatrex40hubspotpartners.com","twenstrandx40hubspotpartners.com","jsaffelx40hubspotpartners.com","obaraschx40hubspotpartners.com","lfughx40hubspotpartners.com","dcuozzox40hubspotpartners.com","kdomanx40hubspotpartners.com","spodraskyx40hubspotpartners.com","scrummellx40hubspotpartners.com","wdabneyx40hubspotpartners.com","bmagnanix40hubspotpartners.com","dharlinx40hubspotpartners.com","mbaumiesterx40hubspotpartners.com"],"name":"Canada","startDate":"2017-05-15"},{"attendeeCount":15,"attendees":["emcgooganx40hubspotpartners.com","dmartensonx40hubspotpartners.com","rlitwilerx40hubspotpartners.com","mmilteerx40hubspotpartners.com","ebechmanx40hubspotpartners.com","dlachiattox40hubspotpartners.com","dkneiselx40hubspotpartners.com","mpeightalx40hubspotpartners.com","tmavromatisx40hubspotpartners.com","lmarsex40hubspotpartners.com","kcortijox40hubspotpartners.com","lvaszilyx40hubspotpartners.com","dhardsockx40hubspotpartners.com","kvannx40hubspotpartners.com","hsalvuccix40hubspotpartners.com"],"name":"Singapore","startDate":"2017-05-02"},{"attendeeCount":14,"attendees":["rcoriax40hubspotpartners.com","bsteltenpohlx40hubspotpartners.com","lpentax40hubspotpartners.com","nnakonechnyx40hubspotpartners.com","jzazozdorx40hubspotpartners.com","dbaldusx40hubspotpartners.com","adejohnx40hubspotpartners.com","ashanholtzerx40hubspotpartners.com","lmcbreartyx40hubspotpartners.com","morizabalx40hubspotpartners.com","kulickix40hubspotpartners.com","atilmonx40hubspotpartners.com","aphilavongx40hubspotpartners.com","farmigerx40hubspotpartners.com"],"name":"United States","startDate":"2017-05-23"},{"attendeeCount":14,"attendees":["fallisx40hubspotpartners.com","pclearmanx40hubspotpartners.com","wbehelx40hubspotpartners.com","esterpkax40hubspotpartners.com","truedx40hubspotpartners.com","bburdinx40hubspotpartners.com","fsomalskix40hubspotpartners.com","pkopischkex40hubspotpartners.com","dsporichx40hubspotpartners.com","acallumx40hubspotpartners.com","tjinx40hubspotpartners.com","gmosleyx40hubspotpartners.com","mvolperx40hubspotpartners.com","cschuethx40hubspotpartners.com"],"name":"Ireland","startDate":"2017-06-14"},{"attendeeCount":14,"attendees":["sfearingx40hubspotpartners.com","fstelbx40hubspotpartners.com","sbrigantinox40hubspotpartners.com","wdevitx40hubspotpartners.com","epronkx40hubspotpartners.com","mblackmanx40hubspotpartners.com","tweltersx40hubspotpartners.com","gabdallax40hubspotpartners.com","dlanfrancox40hubspotpartners.com","jdelnegrox40hubspotpartners.com","anorthamx40hubspotpartners.com","eianaconex40hubspotpartners.com","kkonklex40hubspotpartners.com","hisitax40hubspotpartners.com"],"name":"Japan","startDate":"2017-05-08"},{"attendeeCount":15,"attendees":["atruex40hubspotpartners.com","cdonathx40hubspotpartners.com","dpacox40hubspotpartners.com","jdagnerx40hubspotpartners.com","msciarinix40hubspotpartners.com","jkrenekx40hubspotpartners.com","fembryx40hubspotpartners.com","dhaliburtonx40hubspotpartners.com","dsifrex40hubspotpartners.com","kguerrerox40hubspotpartners.com","mbarwickx40hubspotpartners.com","ttomichekx40hubspotpartners.com","rstraseskiex40hubspotpartners.com","ncamporax40hubspotpartners.com","wnazairex40hubspotpartners.com"],"name":"Mexico","startDate":"2017-05-27"},{"attendeeCount":15,"attendees":["klofthousex40hubspotpartners.com","ecavenessx40hubspotpartners.com","pmccleafx40hubspotpartners.com","afissorix40hubspotpartners.com","dsplanex40hubspotpartners.com","pgerringerx40hubspotpartners.com","jkujakx40hubspotpartners.com","edegenfelderx40hubspotpartners.com","nguthridgex40hubspotpartners.com","lforknerx40hubspotpartners.com","nfeslerx40hubspotpartners.com","eyepsenx40hubspotpartners.com","jangevinex40hubspotpartners.com","vklimczykx40hubspotpartners.com","asprostyx40hubspotpartners.com"],"name":"United Kingdom","startDate":"2017-05-20"},{"attendeeCount":17,"attendees":["matalax40hubspotpartners.com","imosimannx40hubspotpartners.com","sbreemanx40hubspotpartners.com","rdinix40hubspotpartners.com","csudox40hubspotpartners.com","aremlingerx40hubspotpartners.com","mmckerliex40hubspotpartners.com","rpearceyx40hubspotpartners.com","erecordx40hubspotpartners.com","shayworthx40hubspotpartners.com","bbodkinx40hubspotpartners.com","lleavignex40hubspotpartners.com","mochsenbeinx40hubspotpartners.com","ebraaschx40hubspotpartners.com","sroggerox40hubspotpartners.com","jmoatzx40hubspotpartners.com","kcantoralx40hubspotpartners.com"],"name":"France","startDate":"2017-06-25"},{"attendeeCount":17,"attendees":["dweaderx40hubspotpartners.com","tscarfonex40hubspotpartners.com","amahax40hubspotpartners.com","lchienx40hubspotpartners.com","mmilardox40hubspotpartners.com","hjudkinsx40hubspotpartners.com","mkesslerx40hubspotpartners.com","ghofheimerx40hubspotpartners.com","swindauerx40hubspotpartners.com","tcommendatorex40hubspotpartners.com","lconnorx40hubspotpartners.com","eboschex40hubspotpartners.com","ryeungx40hubspotpartners.com","jkreshax40hubspotpartners.com","jdiederichx40hubspotpartners.com","lluckerx40hubspotpartners.com","acerritox40hubspotpartners.com"],"name":"Spain","startDate":"2017-06-29"}]}'

```

![image](https://user-images.githubusercontent.com/19685849/136313713-eae7c532-c006-40dd-ac01-f5aed2a2befe.png)

![image](https://user-images.githubusercontent.com/19685849/136314289-6fa7f419-a9cc-46e5-9f24-71a4d83534ee.png)


PS: When our code runs, we print the curl for the api that is being targeted within the console terminal, which can be useful for debugging purposes. This can be
changed to a text file or other preferred means as it's controlled by a logback.xml file within the resources folder.
This printing is handled by an external library which exists within the pom file.


https://www.linkedin.com/in/francislainycampos/

