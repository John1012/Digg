# Digg

This is Code Challange for <em>Carousell</em> I did.

My strength is software design so that I take much time to design architecture. In Digg, I split into two modules.
1. app(Digg-app):Android Application module
2. core(Digg-core):Core Library module

The goal is that Digg domain should not dependency at any platform. It should be isolated. Because if one day we need to support another platform or make automation testing tool, it's helpful all. 
Another reason is division of work. If our app lacks of User Interface, we can strongly hire engineers who are familiar with UI. Otherwise, I can hire engineers who are familiar with digg domain.

Currently, my idea is that Digg-core adopt clean architecture and Digg-app adopt MVP.

<p><b>Why I prefer to adopt clean architecture in Digg-core?</b></p>
Because dependancy of this structure is very clarity. The source code dependencies can only point inward. 
It means inner circle should not know something about outer circle.
In addition, I choose <em>Repository Pattern</em> at Entities layer. Afterward, whatever use local and remote storage. it doesn't matter.
Finally, I choose <em>Command Pattern</em> to isolate each business logic. It become easy extendibility.


<p><b>Why I prefer to adopt MVP in Digg-app?</b></p>
The only reason is to level up testability. Testability is important activity in the development. Let <em>Presenter</em> become 'middle-man'
between <em>View</em> and <em>Model</em> is good strategy. Because it makes platform-related codes above it. In the future, 
using continous integration(CI) and continous delivery(CD) become easy.

This's developing version, not production. There are a few features I have done.
* list all of topics
* create new topic
* upvote/downvote topic.
* sort via voted amount or creation time

<img src="https://cloud.githubusercontent.com/assets/13214877/25736185/f18d0898-31a2-11e7-8941-887bff01dc41.gif" alt="Demo"/>
