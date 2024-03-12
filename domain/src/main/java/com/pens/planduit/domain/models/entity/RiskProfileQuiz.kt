package com.pens.planduit.domain.models.entity

data class RiskProfileQuiz (
    val number : Int,
    val question : String,
    val choices : List<RiskProfileChoice>,
    var selectedChoice : Int? = null
)

data class RiskProfileChoice (
    val label : String,
    val value : Int
)

// TODO : Remove this dummy data later
val dummyQuestions = listOf(
    RiskProfileQuiz(
        number = 1,
        question = "Berapa lama kamu mau menginvestasikan uangmu",
        choices = listOf(
            RiskProfileChoice(
                label = "Under 18",
                value = 1
            ),
            RiskProfileChoice(
                label = "18-24",
                value = 2
            ),
            RiskProfileChoice(
                label = "25-34",
                value = 3
            ),
            RiskProfileChoice(
                label = "35-44",
                value = 4
            ),
        )
    ),
    RiskProfileQuiz(
        number = 2,
        question = "Berapa besar uang yang kamu ingin investasikan",
        choices = listOf(
            RiskProfileChoice(
                label = "Under 18",
                value = 1
            ),
            RiskProfileChoice(
                label = "18-24",
                value = 2
            ),
            RiskProfileChoice(
                label = "25-34",
                value = 3
            ),
            RiskProfileChoice(
                label = "35-44",
                value = 4
            ),
        )
    ),
    RiskProfileQuiz(
        number = 3,
        question = "Seberapa tinggi toleransi risiko yang bisa kamu tnaggung saat investasi",
        choices = listOf(
            RiskProfileChoice(
                label = "Under 18",
                value = 1
            ),
            RiskProfileChoice(
                label = "18-24",
                value = 2
            ),
            RiskProfileChoice(
                label = "25-34",
                value = 3
            ),
            RiskProfileChoice(
                label = "35-44",
                value = 4
            ),
        )
    ),
    RiskProfileQuiz(
        number = 4,
        question = "Pilih salah satu skenario investasi yang kamu banget!",
        choices = listOf(
            RiskProfileChoice(
                label = "Under 18",
                value = 1
            ),
            RiskProfileChoice(
                label = "18-24",
                value = 2
            ),
            RiskProfileChoice(
                label = "25-34",
                value = 3
            ),
            RiskProfileChoice(
                label = "35-44",
                value = 4
            ),
        )
    ),
    RiskProfileQuiz(
        number = 5,
        question = "Apa saja portofolio investasi yang kamu miliki sampai saat ini?",
        choices = listOf(
            RiskProfileChoice(
                label = "Under 18",
                value = 1
            ),
            RiskProfileChoice(
                label = "18-24",
                value = 2
            ),
            RiskProfileChoice(
                label = "25-34",
                value = 3
            ),
            RiskProfileChoice(
                label = "35-44",
                value = 4
            ),
        )
    ),
    RiskProfileQuiz(
        number = 6,
        question = "Berapa besar total investasi reksadana mu saat ini?",
        choices = listOf(
            RiskProfileChoice(
                label = "Under 18",
                value = 1
            ),
            RiskProfileChoice(
                label = "18-24",
                value = 2
            ),
            RiskProfileChoice(
                label = "25-34",
                value = 3
            ),
            RiskProfileChoice(
                label = "35-44",
                value = 4
            ),
        )
    ),
    RiskProfileQuiz(
        number = 7,
        question = "Apabila salah satu investasi mu mengalami kerugian sebesar 25% dari pokok, hal apa yang akan kamu lakukan?",
        choices = listOf(
            RiskProfileChoice(
                label = "Under 18",
                value = 1
            ),
            RiskProfileChoice(
                label = "18-24",
                value = 2
            ),
            RiskProfileChoice(
                label = "25-34",
                value = 3
            ),
            RiskProfileChoice(
                label = "35-44",
                value = 4
            ),
        )
    ),
)