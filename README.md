# README

## Project Members

* [Nicolas's 33](https://github.com/whattheplay) (Scrum Master, Product Owner, Team Member)
* [Mr. Naveen Anpalagan](https://github.com/breezybbc) (Product Owner, Team Member)

## Documentation

* [Dokumentation](docs/documentation.md)


## Testing

| Section         | Content                          |
| --------------- | -------------------------------- |
| ID              | T-01                             |
| Prerequisites   | Android Studio <br> Emulator on API 29  |
| Procedure       | Wait for Gradle build completion <br> Start the app by running it|
| Expected result | App starting on Emulator |

| Section         | Content                                                                  |
| --------------- | ------------------------------------------------------------------------ |
| ID              | T-02                                                                     |
| Prerequisites   | T-01                                  |
| Procedure       | Wait for Gradle build completion <br> Start the app by running it <br> Wait for successful start on Emulator |
| Expected result | Homepage is shown <br> Page is scrollable <br> There are five cards displayed |

| Section         | Content                                                                  |
| --------------- | ------------------------------------------------------------------------ |
| ID              | T-03                                                                     |
| Prerequisites   | T-02                                  |
| Procedure       | Click burger menu on top left                                         |
| Expected result | Navigation slides into screen <br> The pages "Home Page" and "Overview" are clickable|

| Section         | Content                                                                  |
| --------------- | ------------------------------------------------------------------------ |
| ID              | T-04                                                                     |
| Prerequisites   | T-03                                  |
| Procedure       | Click on Home Page                                        |
| Expected result | Navigation disappears <br> Home Page activity is newly loaded <br> Five different cards are displayed --> random|

| Section         | Content                                                                  |
| --------------- | ------------------------------------------------------------------------ |
| ID              | T-05                                                                     |
| Prerequisites   | T-03                                  |
| Procedure       | Click on Overview                                       |
| Expected result | Navigation disappears <br> Overview activity is loaded|

