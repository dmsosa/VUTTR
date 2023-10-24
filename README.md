## Very Useful Tools To Remember
---

In this challenge, I use to write in German, but the translation is provided within parenthesis when I do so.

### Beschreibung (Description):

The objective is to build a RESTful API with a endpoint called "tools" that allows to perform the CRUD actions that we need to manipulate our set of tools, that is GET, POST, PUT and DELETE tools.

A tool looks like this:

{
        id: 1,
        title: "Notion",
        link: "https://notion.so",
        description: "All in one tool to organize teams and ideas. Write, plan, collaborate, and get organized. ",
        tags: [
            "organization",
            "planning",
            "collaboration",
            "writing",
            "calendar"
        ]
}

### How to use 

This API is easy to use, the steps are below:

#### Steps:

1. Clone this repository in a directory of your preference
2. Open your preferred shell or terminal
3. Go to the directory the repository was cloned in
4. Run `mvwn clean`, then run `mvwn spring-boot:run`
5. The API is listening in the port 3000, use your preferred tool to send requests to it
