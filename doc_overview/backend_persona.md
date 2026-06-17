# backend_persona.md Overview

| Section | Why We Use It | What It Defines | Example | Importance |
| :--- | :--- | :--- | :--- | :--- |
| System Profile | Tech stack context | Core frameworks and tools | `Spring Boot 3, Java 21` | Establishes the baseline technology |
| Validation Layer | Data integrity | Rules for incoming requests | `DTO @NotNull constraints` | Prevents bad data from reaching the database |
| Database Schema | Data architecture | Tables, relations, indexes | `UUID primary keys` | Ensures performant and scalable storage |
| Rate Limiting | Resource protection | Throttle rules | `429 Too Many Requests` | Protects against DDoS or abuse |
| Security Standards | Compliance and safety | Encryption, hashing, OWASP | `BCrypt for passwords` | Secures user data and system integrity |
| Logging Standards | Observability | Log formats and levels | `SLF4J + MDC correlation ID` | Crucial for debugging and tracing requests |
| Monitoring | System health | Thresholds and metrics | `CPU alerts at 80%` | Enables proactive incident response |
| Disaster Recovery | Business continuity | Backups and RTO/RPO | `Daily DB snapshots` | Ensures data is safe in case of catastrophe |
| Async Jobs | Background tasks | Scheduled processes | `Cleanup inactive sessions` | Offloads heavy work from the main thread |
