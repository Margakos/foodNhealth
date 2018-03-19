const messages = {
  successAction: 'Η ενέργεια πραγματοποιήθηκε επιτυχώς',
  errorAction: 'Παρουσιάστηκε σφάλμα κατά την πραγματοποιήση της συγκεκριμένης ενέργειας',
  errorCommunication: 'Παρουσιάστηκε σφάλμα κατά την επίκοινωνία με το διακομιστή. Παρακαλούμε επικοινωνήστε με το διαχειριστή του συστήματος για περισσότερες λεπτομέρειες.',
  errorUnauthorized: 'Δεν υπάρχει χρήστης συνδεδεμένος στην εφαρμογή! Παρακαλούμε συνδεθείτε και προσπαθήστε ξανά',
  errorForbidden: 'Δεν έχετε πρόσβαση στη συγκεκριμένη λειτουργία! Παρακαλούμε συνδεθείτε με διαφορετικό λογαριασμό χρήστη και προσπαθήστε ξανά',
  errorLoad: 'Παρουσιάστηκε σφάλμα κατά τη φόρτωση των δεδομένων',
  errorLogin: 'Λανθασμένη προσπάθεια σύνδεσης! Παρακαλούμε προσπαθήστε ξανά.',
  errorUnknown: 'Η εφαρμογή αντιμετώπισε άγνωστο σφάλμα!  Παρακαλούμε επικοινωνήστε με το διαχειριστή του συστήματος για περισσότερες λεπτομέρειες.',
  errorDuplicateRecord: 'Η εγγραφή αυτή υπάρχει ήδη στη βάση δεδομένων',
  errorDeleteDependencies: 'Η εγγραφή αυτή δεν μπορεί να διαγραφεί, καθώς έχει ήδη διασυνδεθεί με άλλες εγγραφές στη βάση',
  errorPrecontractualNoSteps: 'Η συγκεκριμένη Προσυμβατική δεν έχει βήματα προς εκτέλεση!',
  errorPrecontractualStepNotComplete: 'Το συγκεκριμένο βήμα δεν έχει ακόμα ολοκληρωθεί! Παρακαλούμε συμπληρώστε όλα τα απαραίτητα στοιχεία, προτού προχωρήσετε.',
  errorPrecontractualStepNotSkippable: 'Το συγκεκριμένο βήμα δεν μπορεί να παραληφθεί!',
  errorPrecontractualStepDateNotSet: 'Πρέπει να συμπληρώσετε την "Ημ/νία ολοκλήρωσης Βήματος" και να πατήσετε "Αποθήκευση", προτού μπορέσετε να προχωρήσετε στο επόμενο βήμα',
  warningPrecontractualStepSkipped: 'Το συγκεκριμένο βήμα έχει παραληφθεί, επομένως ενδέχεται να μην έχει συμπληρωμένα όλα τα στοιχεία του.',
  successPrecontractualComplete: 'Η διαδικασία ολοκληρώθηκε επιτυχώς!',
  errorRerunPrecontractualNotEligible: 'Δεν μπορείτε να χρησιμοποιήσετε τη συγκεκριμένη διαδικασία ως επαναληπτική. Παρακαλούμε διαφοροποιήστε τις επιλογές σας και προσπαθήστε ξανά.',
  warningPermissionsChanged: 'Τα δικαιώματα του λογαριασμού χρήστη με τον οποίο είστε συνδεδεμένος/η τροποποιήθηκαν. Παρακαλούμε αποσυνδεθείτε και πραγματοποιήστε εκ νέου είσοδο στην εφαρμογή, προκειμένου να εφαρμοστούν τα νέα.',
  warningUserChanged: 'Ο λογαρισμός χρήστη με τον οποίο είστε συνδεδεμένος/η τροποποιήθηκε. Παρακαλούμε πραγματοποιήστε εκ νέου είσοδο στην εφαρμογή, προκειμένου να εφαρμοστούν οι αλλαγές.',
  warningNoResultFound: 'Δεν βρέθηκαν αποτελέσματα για την αναζήτηση',
  confirmActionTitle: 'Επιβεβαίωση Ενέργειας',
  confirmAction: 'Η ενέργεια αυτή είναι αμετάκλητη. Θέλετε να συνεχίσετε?',
  updateStepAction: 'Είστε σίγουροι ότι θέλετε να συνεχίσετε με την ενημέρωση του βήματος?',
  updateBondInPrecontractualStep: 'Η εγγυητική είναι συνδεδεμένη με προσφορά. Θέλετε να συνεχίσετε με την ενημέρωση?',
  supplierInUse: 'Ο Προμηθευτής χρησιμοποιείται.',
  sectionInUse: 'Ο προμηθευτής που έχει κάνει προσφορά για το Συγκεκριμένο Τμήμα χρησιμοποιείται.',
  stepObjectionRemoval: 'Θέλετε να διαγράψετε και την Ένσταση? Αν πατήσετε όχι θα αποσυνδεθεί απλώς η ένσταση από το Βήμα.',
  updateStepDateConflict (date) {
    return 'Η ημερομηνία ολοκλήρωσης του βήματος είναι μεταγενέστερη από την ημερομηνία ολοκλήρωσης του επόμενου (' + date + '). Θέλετε να συνεχίσετε?'
  },
  selected: 'Επιλεγμένο',
  setSelection: 'Πιέστε Enter για επιλογή',
  removeSelection: 'Πιέστε Enter για αφαίρεση',
  typeToSearch: 'Πληκτρολογείστε για αναζήτηση',
  yes: 'Ναι',
  no: 'Όχι',
  ok: 'Οκ',
  warning: 'Προειδοποίηση',
  warningOverwriteSchedulingFields: 'Οι τιμές των πεδίων του Προγραμματισμού θα αντικατασταθούν από τις αντίστοιχες της επιλεγμένης μελέτης, μόλις πιέσετε "Αποθήκευση"',
  warningOverwriteSchedulingBudget: 'Οι τιμές των πεδίων, καθώς και τα ΚΑΕ/Ποσά του Προγραμματισμού θα αντικατασταθούν από αυτά της επιλεγμένης μελέτης, μόλις πιέσετε "Αποθήκευση"',
  errorNoBudgetEnteredForStudy: 'Η επιλογή δεν μπορεί να γίνει, καθώς η Μελέτη δε διαθέτει ΚΑΕ/Ποσά! Παρακαλούμε συμπληρώστε πρώτα ΚΑΕ/Ποσά για τη Μελέτη και προσπαθήστε ξανά',
  errorExternalSearchNotFound: 'Δε βρέθηκε στη Διαύγεια αναρτημένη πράξη με το συγκεκριμένο ΑΔΑ. Παρακαλούμε ελέγξτε την τιμή του και προσπαθήστε ξανά.',
  errorExternalCommunication: 'Η εξαγωγή του αρχείου από τη Διαύγεια δεν είναι δυνατή. Παρακαλούμε επικοινωνήστε με το διαχειριστή του συστήματος για περισσότερες λεπτομέρειες.',
  infoExternalSearchSelected: 'Έχετε επιλέξει να μεταφορτώσετε έγγραφο από τη Διαύγεια. Εάν επιθυμείτε να ακυρώσετε τη μεταφόρτωση, καθαρίστε το πεδίο \'ΑΔΑ\' πατώντας το σχετικό πλήκτρο.',
  studyDateOvercome: 'Το επιλεγμένο έτος πρέπει να είναι μεγαλύτερο ή ίσο του έτους της Μελέτης',
  schedulingDateOvercome: 'Το επιλεγμένο έτος πρέπει να είναι μεγαλύτερο ή ίσο του έτους του Προγραμματισμού',
  required: 'Το πεδίο δεν έχει συμπληρωθεί.',
  EDITING: 'Σε επεξεργασία',
  ON_HOLD: 'Σε αναμονή',
  APPROVED: 'Εγκεκριμένο',
  REVISED: 'Θεωρημένο',
  RETURNED_FOR_CORRECTIONS: 'Επιστραμένο για διορθώσεις',
  NOTREVISED: 'Μη Θεωρημένο',
  REJECTED: 'Απορριφθέν',
  // Bond Types
  PARTICIPATION: 'Συμμετοχής',
  PERFORMANCE: 'Καλής Εκτέλεσης',
  PERFORMANCE_AGREEMENT: 'Καλής Εκτέλεσης συμφωνίας-πλαίσιο',
  DEPOSIT: 'Προκαταβολής',
  OPERATION: 'Καλής Λειτουργίας',
  //
  DIGITAL_ENVELOP: 'Φάκελος Σύμβασης',
  TREASURY: 'Ταμείο',
  RETURNED: 'Έχει Επιστραφεί',
  FORFEITURE: 'Κατάπτωση',
  ENROLLED_IN_SCHEDULING: 'Ενταγμένο στον Προγραμματισμό',
  ENROLLED_IN_BUDGET: 'Ενταγμένο στον Προϋπολογισμό',
  IN_PRECONTRACTUAL: 'Σε Προσυμβατική',
  // precontractual statuses
  IN_PROGRESS: 'Σε εξέλιξη',
  CANCELLED: 'Ακύρωση',
  NO_AWARD: 'Άγονος χωρίς επανάληψη',
  NO_AWARD_RERUN: 'Άγονος με επανάληψη',
  NO_AWARD_PARTIAL: 'Μερικώς Άγονος',
  RESUMED: 'Συνέχιση από προηγούμενο βήμα',
  COMPLETED: 'Ολοκληρώθηκε',
  // precontractual types
  PRECONTRACTUAL_NEW: 'Νέα',
  PRECONTRACTUAL_RERUN: 'Επαναληπτική',
  // precontractual check messages
  amountExceedDirect: 'Προσοχή ξεπερνάτε το ανώτατο όριο της απευθείας ανάθεσης. Μήπως εννοούσατε προσφυγή σε διαδικασία διαπραγμάτευσης χωρίς δημοσίευση? Θέλετε να συνεχίσετε?',
  amountExceedSynoptic: 'Προσοχή ξεπερνάτε το ανώτατο όριο των 60.000,00 € χωρίς ΦΠΑ του συνοπτικού. Παρακαλώ όπως διορθώσετε τη διαδικασία ανάθεσης.',
  studyAmountExceedDirect: 'Προσοχή ξεπερνάτε το ανώτατο όριο της απευθείας ανάθεσης. Μήπως εννοούσατε προσφυγή σε διαδικασία διαπραγμάτευσης χωρίς δημοσίευση? Θέλετε να συνεχίσετε?',
  studyAmountExceddConcise: 'Προσοχή ξεπερνάτε το ανώτατο όριο των 60,000,00 € χωρίς ΦΠΑ του συνοπτικού. Παρακαλώ όπως διορθώσετε τη διαδικασία ανάθεσης',
  precontractualBudgetExceed (budgetCode, year, amount) {
    return 'Ο ΚΑΕ με κωδικό ' + budgetCode + ' του έτους ' + year + ' δεν έχει επαρκές υπόλοιπο (υπολοιπούμενο: ' + amount + '€). Εφόσον δεν κάνετε πολυετή δέσμευση πρέπει να κάνετε αναμόρφωση. Θέλετε να συνεχίσετε?'
  },
  errorVAT: 'Το ποσό με ΦΠΑ πρέπει να είναι μεγαλύτερο του ποσού χωρίς ΦΠΑ',
  errorBudgetAmount: 'Το ποσό του προϋπολογισμού πρέπει να είναι μεγαλύτερο ή ίσο του ποσού σύμβασης με ΦΠΑ',
  // Contract statuses
  ACTIVE: 'Ενεργή',
  EXTENDED: 'Σε Τροποποίηση',
  COMPLETE_PHYSICAL_OBJECT: 'Ολοκλήρωση Φυσικού Αντικειμένου',
  COMPLETE: 'Ολοκληρωμένη',
  PAUSED: 'Σε Διακοπή',
  DESTRUCTED: 'Σε Διάλυση',
  COMPLETELY_DESTRUCTED: 'Σε οριστική Διάλυση',
  REVERT: 'Επαναφορά',
  // END precontractual statuses
  OBJECTION: 'Ένσταση',
  APPEAL: 'Προσφυγή',
  EXAMINED: 'Σε εξέταση',
  COMPETENT_AUTHORITY: 'Αποφαινόμενο Όργανο',
  // ContractAmendment Types
  AMENDMENT: 'Τροποποίηση',
  INITIAL: 'Αρχική',
  // Document Types
  OTHER: 'Άλλο',
  MAIN: 'Κύριο έγγραφο',
  PRIMARY_REQUEST: 'Πρωτογενές Αίτημα',
  OBLIGATION_UNDERTAKING_SUGGESTION: 'Πρόταση Ανάληψης Υποχρέωσης',
  RULING: 'Γνωμοδότηση',
  OPINION: 'Άποψη',
  DECISION: 'Απόφαση',
  FORMAL_DECLARATION: 'ΤΕΥΔ',
  EUROPEAN_SINGLE_PROCUREMENT_DOCUMENT: 'ΕΕΕΣ',
  CLARIFICATION: 'Διευκρίνιση',
  MEMORANDUM: 'Υπόμνημα',
  ANSWER: 'Απάντηση Αναθέτουσας Αρχής/Φορέα',
  TECHNICAL_SPECIFICATION: 'Τεχνικές Προδιαγραφές',
  FINANCIAL_OFFER: 'Οικονομική Προσφορά',
  REVOCATION_REQUEST: 'Αίτηση Αναθεώρησης',
  REVIEW_REQUEST: 'Αίτηση Ανάκλησης',
  CHECK_RESULT: 'Έγγραφο Αποτελέσματος Ελέγχου',
  CORRESPONDENCE: 'Αλληλογραφία',
  SUPPORTING_DOCUMENTATION: 'Δικαιολογητικά Συμμετοχής',
  TECHNICAL_OFFER: 'Τεχνική Προσφορά',
  ASSIGNMENT: 'Ανάθεση',
  ADA_FILE: 'ΑΔΑ',
  ADAM_FILE: 'ΑΔΑΜ',
  VIEW: 'Γνώμη',
  DISPATCH_DATE: 'Ημερομηνία Αποστολής',
  LOTTERY_MINUTES: 'Πρακτικό Κλήρωσης',
  ALL: 'Όλα',
  TEMPORARY_CONCESSIONAIRE_DOCUMENT_SUBMISSION_DATE: 'Καταληκτική Ημ/νια Προσκόμισης Αρχείων Προσ. Αναδόχου',
  POSTINGS_CONTEST_DATE: 'Ημ/νια Διαγωνισμού',
  CONTRACT_EXPIRATION: 'Ημ/νια Λήξης Σύμβασης',
  UPDATED_DOCUMENT_SUBMISSION_DATE: 'Καταληκτική Ημ/νια Προσκόμισης Επικαιροποιημένων Εγγράφων',
  CONTRACT_SIGNING_DATE: 'Καταληκτική Ημ/νια Υπογραφής Σύμβασης',
  EFFECTIVE_CONTRACT_SIGNING_DATE: 'Ημ/νια Υπογραφής Σύμβασης',
  AMOUNT_DATE_AMENDMENT: 'Τροποποίηση Ημερομηνίας και Ποσών',
  DATE_ONLY_AMENDMENT: 'Τροποποίηση Ημερομηνίας',
  AMOUNT_ONLY_AMENDMENT: 'Τροποποίηση Ποσών',
  PAUSED_AMENDMENT: 'Διακοπή',
  DESTRUCTED_AMENDMENT: 'Διάλυση',
  COMPLETELY_DESTRUCTED_AMENDMENT: 'Οριστική Διάλυση',
  REVERT_AMENDMENT: 'Επαναφορά',
  MALE: 'Άνδρας',
  FEMALE: 'Γυναίκα',
  COMMISSIONER: 'Επίτροπος',
  UNIT: 'Κλιμάκιο',
  DIRECT_ASSIGNMENTS: 'απευθίας αναθέσεων',
  SYNOPTIC_CONTESTS: 'συνοπτικών',
  // Pages (i.e. routes)
  Precontractuals: 'Προσυμβατικές',
  PrecontractualsStatistics: 'Στατιστικά Προσυμβατικών',
  ContractsStatistics: 'Στατιστικά Συμβάσεων',
  PrecontractualReports: 'Αναφορές Προσυμβατικών',
  PrecontractualsProgress: 'Πορεία Προσυμβατικών',
  ContractRemainderReports: 'Υπόλοιπα Συμβάσεων',
  FinancialCommitmentsRemainderReports: 'Υπόλοιπα Δεσμεύσεων',
  ContractBudgetDeviationsReport: 'Αποκλίσεις Προϋπολογισμού - Σύμβασης',
  // Steps
  ActionApprovalStep: 'Έγκριση Διενέργειας',
  AttributionDecisionStep: 'Απόφαση Ανάληψης / Εγκεκριμένο Αίτημα',
  AwardDecisionStep: 'Απόφαση Ανάθεσης',
  ContractSigningStep: 'Υπογραφή Σύμβασης',
  DeclarationApprovalStep: 'Έγκριση Διακήρυξης/Τεχνικών Προδιαγραφών',
  ElectronicLotteryStep: 'Ηλεκτρονική Κλήρωση',
  ExpenseApprovalStep: 'Έγκριση Δαπάνης/Διάθεση Πίστωσης',
  LegalCheckSubmissionStep: 'Αποστολή Προς Ελεγκτή Νομιμότητας',
  PostingsStep: 'Δημοσιεύσεις/Προσκλήσεις',
  PrecontractualCheckStep: 'Προσυμβατικός Έλεγχος',
  PrimaryRequestStep: 'Πρωτογενές Αίτημα/Πρόταση Ανάληψης Υποχρέωσης',
  ProcurementDecisionStep: 'Απόφαση Κατακύρωσης',
  SessionsStep: 'Συνεδριάσεις/Πρακτικά',
  StudyStep: 'Μελέτη - Τεχνικές Προδιαγραφές',
  TechnicalBoardRulingStep: 'Έκδοση Γνώμης Τεχνικού Συμβουλίου',
  TechnicalSpecificationsApprovalStep: 'Έγκριση Τεχνικών Προδιαγραφών',
  TemporaryConcessionaireStep: 'Προσωρινός Ανάδοχος',
  TemporaryConcessionaireCheckStep: 'Έλεγχος Προσωρινού Αναδόχου',
  RequestCheckStep: 'Έλεγχος Αιτήματος',
  ActSigningStep: 'Υπογραφή Πράξης',
  // Contract
  MilestoneActualDurationExceedContract: 'Προσοχή ξεπερνάτε την ημερομηνία λήξης της σύμβασης. Παρακαλώ είτε αλλάξτε τη διάρκεια είτε προβείτε στις κατάλληλες ενέργειες για παράταση',
  MilestoneDurationExceedContract: 'Δεν μπορεί η ημερομηνία στόχος αυτής της φάσης να είναι μεγαλύτερη από την ημερομηνία λήξης της σύμβασης',
  SupplierFieldUncompleted: 'Δεν έχει συμπληρωθεί το πεδίο του Προμηθευτή. Συμπληρώστε το προκειμένου να συνεχίσετε μέσω "Δημιουργία νέας Σύμβασης εξαρχής" ή "Δημιουργία νέας Σύμβασης από Προσυμβατική"',
  ContractSigningIsNotSelected: 'Επιλέξτε προμηθευτή για να συμπληρωθούν αυτόματα ορισμένα υποχρεωτικά πεδία',
  // Bond
  BondDurationAndExpirationDateEmpty: 'Τα πεδία "Ημερομηνία Λήξης" και "Διάρκεια" δεν γίνεται να είναι ταυτόχρονα κενά. Συμπληρώστε το ένα από τα δύο',
  // Contexts
  Study: 'Μελέτη',
  Proposal: 'Πρόταση',
  Scheduling: 'Προγραμματισμός',
  Contract: 'Σύμβαση',
  Objection: 'Ένσταση-Προσφυγή',
  Precontractual: 'Προσυμβατική',
  Bond_Entity: 'Εγγυητική',
  Budget: 'Προϋπολογισμός',
  Auxiliary: 'Βοηθητικά (Προμηθευτές-Επιτροπές)',
  Lookup: 'Διαχείριση',
  Notification: 'Ειδοποιήσεις',
  Statistics: 'Στατιστικά',
  studySectionRemoveConfirmation: 'Πρόκειται να αφαιρέσετε ένα τμήμα μελέτης. Είστε σίγουροι?',
  removeAllStudySectionsConfirm: 'Πρόκειται να αφαιρέσετε όλα τα συνδεδεμένα τμήματα μελέτης. Είστε σίγουροι?',
  budgetSchemaYearBeforeCurrentYear: 'Οι ΚΑΕ που έχετε στη μέλετη είναι διαφορετικού έτους απο το τρέχον. Θέλετε να συνεχίσετε?',
  // Checks
  cpvPartitioningProcessCheckErrorMessage (message) {
    return message + ' \nΘέλετε να συνεχίσετε?'
  },
  budgetSchemaPartitioningProcessCheckErrorMessage (message) {
    return message + ' \nΘέλετε να συνεχίσετε?'
  },
  cpvPartitioningAmountCheckErrorMessage (message) {
    return message + ' \nΘέλετε να συνεχίσετε?'
  },
  budgetSchemaPartitioningAmountCheckErrorMessage (message) {
    return message + ' \nΘέλετε να συνεχίσετε?'
  },
  budgetSchemasNotFilledInStudy (sections) {
    return 'Tα τμήματα "' + sections + '", της μελέτης δεν έχουν συμπληρωμένο ΚΑΕ. Παρακαλώ συμπληρώστε τα ώστε να μπορέσει να συνδεθεί στην Προσυμβατική'
  },
  passwordMismatch: 'Οι κωδικοί που εισάγατε δεν ταιριάζουν',
  wrongExistingPassword: 'Ο κωδικός που εισάγατε δεν ταιριάζει με τον υφιστάμενο κωδικό',
  RequestCheckResultResumed: 'Προσοχή! Επιλέξατε αποτέλεσμα "Συνέχιση από προηγούμενο βήμα". Η ενέργεια αυτή είναι αμετάκλητη. Θέλετε να συνεχίσετε?',
  RequestCheckResultRejected: 'Προσοχή! Επιλέξατε αποτέλεσμα "Απορριφθέν". Η ενέργεια αυτή είναι αμετάκλητη. Θέλετε να μεταβάλλεται την κατάσταση του διαγωνισμού?',
  amendmentDateBeforeContractDate (contractDate) {
    return 'Η ημ/νία Λήξης δεν μπορεί να είναι προγενέστερη της ημ/νίας έναρξης της Σύμβασης (' + contractDate + ')'
  },
  contractAmendmentDateInvalid: 'Δεν μπορείτε να βάλετε τροποποίηση με αυτή την Ημ/νία Τροποποίησης διότι υπάρχουν τροποποιήσεις με μεταγενέστερη Ημ/νία Τροποποίησης',
  revertIsNotApplicable: 'Δεν μπορείτε να βάλετε τροποποίηση τύπου "Επαναφορά" αν δεν υπάρχει έστω μια εκκρεμής εγγραφή τύπου "Διάλυση" ή τύπου "Διακοπή"',
  studyNumberAlreadyExists: 'Ο Αριθμός Μελέτης υπάρχει ήδη σε κάποια έγγραφη του έτους.',
  endDateBeforeAmendmentDate: 'Η ημ/νία Λήξης δεν μπορεί να είναι προγενέστερη της ημ/νίας Τροποποίησης',
  amountMoreThanInvoices (amount) {
    return 'Το ποσό τροποποίησης πρέπει να είναι μεγαλύτερο από το άθροισμα των τιμολογίων (' + amount + '€)'
  }
}

export default messages
