# BunkerFinal: Enterprise Lifecycle & Auth Sandbox

## 🏛️ Overview
**BunkerFinal** is a research-oriented repository focusing on Jakarta EE (Java EE) lifecycle management, authentication flow control, and component-driven architecture. This lab serves as the dedicated stress-testing environment for high-reliability web deployments and the reverse-engineering of legacy integration patterns commonly found in enterprise financial systems.

## 🌐 The Enterprise Research Suite

This repository is architected into two primary research modules:

1. **BunkerFinal (Middleware & Auth Control):** Focuses on security-first session management and HTTP transaction logic. It acts as a middleware sandbox for validating state-machine transitions and enforcing secure request/response cycles at the application perimeter.
2. **SmartNumberGuesser (SNG - Lifecycle Optimization):** A deep-dive analysis of JSF (JavaServer Faces) lifecycle management and asynchronous state management. It provides a blueprint for identifying architectural bottlenecks and component behavior in large-scale enterprise containers.

## 🛠️ Project Structure
```text
java-lab/
├── BunkerFinal/              # Auth & Flow Control Module
│   ├── index.jsp             # Core HTTP/Presentation logic
│   └── build.xml             # Apache Ant deployment automation
└── SmartNumberGuesser/       # JSF Lifecycle Sandbox
    ├── index.xhtml           # Async component-driven view
    └── sng_arch_v4.html      # Architectural blueprint & analysis
