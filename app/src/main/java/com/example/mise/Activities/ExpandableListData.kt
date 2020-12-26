package com.example.mise.Activities

internal object ExpandableListData {
    val data: HashMap<String, List<String>>
        get() {
            val expandableListDetail =
                HashMap<String, List<String>>()

            val whoWeAreMsg: MutableList<String> = ArrayList()
            whoWeAreMsg.add("Incorporated in the year 2009, MINERVAA stands firm to the\n" +
                    "primary objective of imparting quality education among the\n" +
                    "aspiring students. MINERVAA is thriving to set a benchmark in\n" +
                    "the field of academics. Besides, instilling knowledge\n" +
                    "MINERVAA actively contributes to the growth & welfare of\n" +
                    "charitable trusts & organizations as we believe in our vision of\n" +
                    "promulgating education to every individual of the country. The\n" +
                    "organization has spearheaded innumerable present-day\n" +
                    "programs in the field of education to nurture younger talents\n" +
                    "make them future-ready & equally contribute to the progress of\n" +
                    "skilled Human Resources alongside.")
            expandableListDetail["WHO WE ARE"] = whoWeAreMsg

            val whatWeDoMsg: MutableList<String> = ArrayList()
            whatWeDoMsg.add("MISE envisions the notion of practicality & believes in\n" +
                    "inculcating knowledge in the most pertinent way. Ensuring that\n" +
                    "the students are able to grasp even the tiniest bit of information.\n" +
                    "Furthermore, MISE entices an innovative approach to learning\n" +
                    "over innumerable educative platforms. A scholarship\n" +
                    "assessment program that covers all aspects of dignifying\n" +
                    "subjects like English, Mathematics, Science, General\n" +
                    "Knowledge, Computer Science, Social Studies & Fine Arts.")
            expandableListDetail["WHAT WE DO?"] = whatWeDoMsg

            val aboutMiseMsg: MutableList<String> = ArrayList()
            aboutMiseMsg.add("MISE or Minervaa International Scholarship Examination is one\n" +
                    "of the most noteworthy & prestigious examinations conducted\n" +
                    "for students from class KG to XII. A scientifically standardized\n" +
                    "assessment evaluation, available both online & offline is meant\n" +
                    "for students who aspire to make progress with their academic\n" +
                    "aptitude. The examination is conducted on a yearly interval\n" +
                    "where educational institutions at the national & international\n" +
                    "level actively participate to ensure that their students get a\n" +
                    "\n" +
                    "chance to compete with their peers in subjects such as English,\n" +
                    "Mathematics, Abacus, Fine Arts, Computer Science & so on.\n" +
                    "By participating in these scholarship exams, students are\n" +
                    "encouraged to perform better in their academics & further,\n" +
                    "acknowledge the significance of taking scholarship\n" +
                    "examinations. As recognition of the students’ consistent effort,\n" +
                    "MISE rewards the students with assorted gift hampers. Thus,\n" +
                    "motivating the students, teachers & parents to insinuate active\n" +
                    "participation in the scholarship exams.")
            expandableListDetail["ABOUT MISE"] = aboutMiseMsg

            val ourTeamMsg: MutableList<String> = ArrayList()
            ourTeamMsg.add("The MISE team comprises highly motivated & proficiently\n" +
                    "skilled professionals. Dedicated to inculcating quality education\n" +
                    "services & a vision to inspire the younger minds to think & act\n" +
                    "better. Our team consists of exceptional mentors, specializing\n" +
                    "in every aspect of academics helping the students hone\n" +
                    "proficiency & guidance in the recurring field of education &\n" +
                    "self-growth.")
            expandableListDetail["OUR TEAM"] = ourTeamMsg


            /*expandableListDetail.put("WHO WE ARE", whoWeAreMsg);
            expandableListDetail.put("ABOUT MISE", aboutMiseMsg);
            expandableListDetail.put("OUR TEAM", ourTeamMsg);
            expandableListDetail.put("WHAT WE DO?", whatWeDoMsg);*/
            return expandableListDetail
        }
}