package com.interview.platform.domain.model.mod14_ai_evaluation

data class EvaluationResult(
    val id: String,
    val overallScore: Double, // e.g. 85.0
    val totalQuestions: Int,
    val date: String,
    val timeElapsed: String,
    val categoryScores: CategoryScores,
    val questionAnalyses: List<QuestionAnalysis>,
    val overallFeedback: OverallFeedback
)

data class CategoryScores(
    val technicalAccuracy: Double,
    val clarityAndCommunication: Double,
    val problemSolving: Double,
    val completeness: Double
)

data class QuestionAnalysis(
    val questionId: String,
    val questionNumber: Int,
    val questionText: String,
    val expectedAnswer: String,
    val providedAnswer: String,
    val score: Double,
    val isCorrect: Boolean,
    val aiFeedback: String,
    val keyConceptsHit: List<String>,
    val keyConceptsMissed: List<String>
)

data class OverallFeedback(
    val strengths: List<String>,
    val weaknesses: List<String>,
    val actionableSteps: List<String>
)

// Mock Data Provider for UI Testing
object MockEvaluationProvider {
    val mockResult = EvaluationResult(
        id = "eval_101",
        overallScore = 85.0,
        totalQuestions = 3,
        date = "Oct 28, 2023",
        timeElapsed = "45:20",
        categoryScores = CategoryScores(
            technicalAccuracy = 88.0,
            clarityAndCommunication = 82.0,
            problemSolving = 90.0,
            completeness = 80.0
        ),
        questionAnalyses = listOf(
            QuestionAnalysis(
                questionId = "q1",
                questionNumber = 1,
                questionText = "Explain the difference between a process and a thread.",
                expectedAnswer = "A process is an independent program in execution with its own memory space. A thread is a lightweight unit of execution within a process, sharing the same memory space.",
                providedAnswer = "A process is a program that is running, and a thread is a part of that process. Threads run inside processes and they share memory, while processes have their own memory.",
                score = 90.0,
                isCorrect = true,
                aiFeedback = "Excellent understanding of the core concepts. You correctly identified memory isolation as the key difference.",
                keyConceptsHit = listOf("Independent execution", "Shared memory in threads"),
                keyConceptsMissed = listOf("Lightweight vs heavyweight context switching")
            ),
            QuestionAnalysis(
                questionId = "q2",
                questionNumber = 2,
                questionText = "What is a Deadlock and what are the four necessary conditions for it?",
                expectedAnswer = "A deadlock is a situation where a set of processes are blocked because each process is holding a resource and waiting for another resource acquired by some other process. The four conditions are Mutual Exclusion, Hold and Wait, No Preemption, and Circular Wait.",
                providedAnswer = "Deadlock happens when two processes wait for each other forever. I think the conditions are mutual exclusion, hold and wait, and circular wait.",
                score = 70.0,
                isCorrect = false,
                aiFeedback = "You got the definition right and 3 out of 4 conditions. You missed the 'No Preemption' condition.",
                keyConceptsHit = listOf("Mutual Exclusion", "Hold and Wait", "Circular Wait"),
                keyConceptsMissed = listOf("No Preemption")
            ),
            QuestionAnalysis(
                questionId = "q3",
                questionNumber = 3,
                questionText = "How does virtual memory work?",
                expectedAnswer = "Virtual memory uses hardware and software to allow a computer to compensate for physical memory shortages by temporarily transferring data from RAM to disk storage. It uses paging and page tables to map virtual addresses to physical addresses.",
                providedAnswer = "Virtual memory makes the computer think it has more RAM than it actually does by using the hard drive. It swaps pages of memory back and forth.",
                score = 85.0,
                isCorrect = true,
                aiFeedback = "Good high-level explanation. Mentioning 'page tables' and 'virtual to physical mapping' would make this answer perfect.",
                keyConceptsHit = listOf("Compensate physical memory", "Disk storage swap"),
                keyConceptsMissed = listOf("Page tables", "Virtual address mapping")
            )
        ),
        overallFeedback = OverallFeedback(
            strengths = listOf("Strong understanding of core OS concepts", "Clear communication of complex ideas"),
            weaknesses = listOf("Sometimes misses edge-case conditions (e.g., No Preemption)"),
            actionableSteps = listOf("Review the specific conditions for concurrency issues", "Practice explaining the lower-level hardware/software interactions")
        )
    )
}
