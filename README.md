# Sheriff’s Word Hunt

Sheriff’s Word Hunt is a playful and immersive language game designed for children ages 10–13.
Players step into the role of junior deputies in a Wild West town where a mysterious bandit has been stealing words from sentences. Their mission is to solve language puzzles, catch the culprit, and restore order — one word at a time.

## Table of Contents
- [UX](#ux)
    - [App Purpose](#app-purpose)
    - [App Goal](#app-goal)
    - [Developer Goals](#developer-goals)
    - [User Goals](#user-goals)
    - [Audience](#audience)
    - [Communication](#communication)
    - [Interaction & Experience Principles](#interaction--experience-principles)
- [Agile Planning](#agile-planning)
    - [Epic](#epic)
    - [User Stories](#user-stories)
        - [Implemented User Stories](#implemented-user-stories)
        - [Not implemented User Stories](#not-implemented-user-stories)
    - [MoSCoW Prioritization](#moscow-prioritization)
    - [Kanban Board](#kanban-board)
    - [UML Diagram](#uml-diagram)
- [Design](#design)
    - [Wireframe](#wireframe)
    - [Colour Scheme](#colour-scheme)
    - [Fonts](#fonts)
- [Features](#features)
    - [Existing Features](#existing-features)
    - [Future Features](#future-features)
- [Testing](#testing)
    - [Manual Testing](#manual-testing)
    - [Bugs](#bugs)
    - [Unfixed Bugs](#unfixed-bugs)
- [Technologies](#technologies)
    - [Main Languages Used](#main-languages-used)
    - [Setup & Installation](#setup--installation)
- [Credits](#credits)
    - [Content](#content)
    - [Media](#media)

## UX
### App Purpose
Sheriff’s Word Hunt helps children practice language and reading comprehension through short, game-like word puzzles wrapped in a playful Wild West theme.

### App Goal
The goal is to create a motivating and intuitive learning experience where children can strengthen vocabulary skills without feeling like they are doing schoolwork.

### Developer Goals
- Maintain clean, testable architecture
- Use a Repository Pattern for puzzle logic
- Keep Activities simple and focused
- Use ViewBinding for safer UI handling

### User Goals
- Quickly understand how to play
- Solve puzzles with minimal frustration
- Receive instant feedback
- Progress smoothly between challenges
- Stay motivated through a simple, playful theme

### Audience
Children ages 10–13.  
Designed to be usable by all children, but visually and structurally supportive for users who benefit from reduced cognitive load (e.g., ADHD, autism, dyslexia).

### Communication
The app communicates through clear buttons, minimal choices, simple text, and short feedback toasts. The theme supports motivation while keeping the interface calm and predictable.

### Interaction & Experience Principles
- Clear and predictable navigation
- Limited choices per screen
- Short non-blocking feedback (toasts)
- Smooth transitions between puzzles
- Calming, low-contrast visuals
- Reduced cognitive load through simple layouts

## Agile Planning
### Epic
### User Stories
#### Implemented User Stories
#### Not implemented User Stories
### MoSCoW Prioritization
### Kanban Board

### UML Diagram
The UML diagram was created in [Lucidchart](https://www.lucidchart.com/) and outlines the flow between Activities, Fragments, and the Repository layer. It serves as a blueprint for the app’s architecture and game progression.

![UML Diagram](docs/uml_diagram.png)

## Design
The design of Sheriff’s Word Hunt focuses on creating a calm, readable, and thematic visual experience. 
The Western-inspired colour palette, textured cards, and simple screen layouts support a low-cognitive-load 
environment while keeping the game playful and engaging.

### Colour Scheme
Sheriff’s Word Hunt uses a limited colour palette supported by textured paper elements to reinforce the Wild West atmosphere and maintain a calm, readable interface for children.

The colors were sampled from the in-app designs created in [Figma](https://www.figma.com/), and the final palette layout was assembled using [Coolors](https://coolors.co/).

- **Primary background / card color:** `#872606`  
A deep Western red inspired by worn saloon signs and wanted posters, forming the main mood of the UI.

- **Toast feedback background:** `#C19468`  
A warm paper tone used for short success/failure feedback messages, providing softer contrast and reducing visual strain.

- **Text color:** `#FFEFC5`  
A light sand color used instead of pure white to reduce glare and improve readability.

This low-contrast, texture-driven UI supports a **more consistent and less visually overwhelming experience**, which may also benefit players who prefer predictable and calmer visual environments, including some neurodivergent users.

*Color Palette* 

![Color Palette](docs/color_palette.png)

### Wireframe
The wireframes were created in [Figma](https://www.figma.com/) and illustrate the core navigation structure of the app:

![Wireframes](docs/wireframes.png)

### Fonts
Sheriff’s Word Hunt uses two fonts chosen to balance Wild West style with child-friendly readability:

- **Rye** - Used only for the main game title on the hero screen. This font gives a classic Wild West poster look and helps set the theme immediately.

- **Roboto Slab** - Used for all other text in the app, including:
  - Subtitle on the hero screen
  - Buttons
  - Body text
  - Headings (except hero title)
  - Toast messages
  - Cards, labels, and UI elements

Roboto Slab is used in multiple weights (Regular and Light) to keep the interface calm, readable, and visually consistent.
Its clean geometric shapes and soft serifs support accessibility, especially for children who may struggle with high-contrast or overly decorative text (e.g., dyslexia, ADHD).

## Features
### Existing Features
### Future Features
## Testing
### Manual Testing
### Bugs
### Unfixed Bugs
## Technologies
### Main Languages Used
### Setup & Installation
## Credits
### Content
### Media
- All decorative images and graphical assets are sourced from [Freepik](https://www.freepik.com/).


