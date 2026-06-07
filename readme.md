# ⚡ Advanced Java Laboratory (`java-lab`)

Welcome to the centralized repository for advanced Java EE development. This laboratory aggregates modularized software solutions focused on JavaServer Faces (JSF) lifecycle manipulation, structural decoupling, and asynchronous dynamic web interfaces.

---

## 📂 Repository Structure

java-lab/
├── README.md                  # Global Operations Index (This file)
│
├── BunkerFinal/               # Authentication & Flow Control Module (JSP)
│   ├── index.jsp              # Presentation layer & core HTTP logic
│   └── build.xml              # Apache Ant deployment automation script
│
└── SmartNumberGuesser/        # JSF Lifecycle Optimization Sandbox
    ├── index.xhtml            # Async component-driven JSF view
    └── sng_arch_v4.html       # Architecture blueprint & reverse-engineering log

---

## 🎯 Project 1: BunkerFinal
* **Technologies:** Java EE, JSP (JavaServer Pages), Apache Ant.
* **Core Logic:** A lightweight monolithic system focused on the structured processing of HTTP requests. It utilizes an automation script based on build.xml to handle rapid deployment, resource compilation, and artifact packaging for the application server target.

---

## 🧠 Project 2: Smart Number Guesser v4.0 (Dev Dashboard Edition)
* **Technologies:** Java EE 8, JSF 2.2 (JavaServer Faces), Expression Language (EL), GlassFish 5.0.
* **Backend Controller:** Managed via the @Named and @SessionScoped CDI bean named adivinaBean.

### 💻 UI & Layout Engine
Breaks away from traditional monolithic JSF visual paradigms by implementing a responsive, dark-themed, triple-panel layout:
* **panel-izquierdo:** Global status monitor and real-time session statistics.
* **panel-derecho:** Live game engine console and transaction data input.
* **panel-inferior:** Sequential historical system event console.

### 🎮 Punitive Gamification & Proximity Feedback
The system intercepts user transactions within the procesarIntento method to evaluate active performance:
* **Recidivism Penalty:** If the user inputs corrupted data or submits the same number consecutively, the backend logic deducts a life heart punitively.
* **calcularFeedbackCalor Iterative Algorithm:** The mathematical differential between the input and the secret number is processed through a proximity engine named after open-source and computing pioneers:
  * **Distance [1 - 3]:** Linus Torvalds / Ada Lovelace Level (Kernel Layer / Critical Proximity).
  * **Distance [4 - 8]:** Dennis Ritchie / Alan Turing Level (Decrypting the Backend).
  * **Distance [9 - 15]:** Grace Hopper / Richard Stallman Level (Debugging the Bug / Open Source).

### 🛠️ Implemented JSF Lifecycle Hacks
The core of the project highlights the resolution of three critical behavioral issues inherent to the JSF 2.2 framework:

1. **Infinite Lives Bug Bypass (Phase 3 vs Phase 5):** When entering text strings or out-of-bounds numbers, JSF's native validators intercepted the error during the Process Validations Phase (Phase 3), aborting the lifecycle and jumping directly to Render Response (Phase 6). This caused the business logic in Phase 5 to be skipped entirely, granting the user "infinite lives".
   * **Solution:** Mutated the model data type to a native String to force JSF past Phase 3, successfully reaching Invoke Application (Phase 5). There, a manual try-catch block intercepts the NumberFormatException, sanitizing the input and decrementing the life heart safely.

2. **Reactive Bridge Against AJAX Focus Loss:** Partial asynchronous requests via <f:ajax render="@form"> dynamically destroyed and reinserted the form node into the DOM, causing the browser to drop the physical keyboard focus from the input field after each submission.
   * **Solution:** Injected the onevent="mantenerFocoTeclado" attribute into the AJAX tag. This JavaScript routine intercepts the success state of the JSF transmission channel, programmatically restoring focus instantly and triggering a .select() on the mutated element.

3. **Decoupling the "Suicidal Enter" Event:** Per HTML5 specifications, pressing the Enter key within a web form automatically triggers a submit action on the first declarative button found in the hierarchical tree. In JSF, this accidentally executed the "Reset Game" button mid-match.
   * **Solution:** Performed perimetral isolation of the component trees using strategic execution attributes and suppressing cross-boundary submit events within the DOM.