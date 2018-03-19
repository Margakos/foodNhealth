// import VeeValidate from 'vee-validate'

const messages = {
  // after: (field, [target]) => `${field} πρέπει να είναι μεταγενέστερο του ${target}.`,
  // Example follows
  after: (field, [target]) => `Η τιμή του αρχικού πεδίου πρέπει να είναι μεταγενέστερη της τιμής του πεδίου "${target}"`,
  alpha_dash: (field) => `Το πεδίo μπορεί να περιέχει αλφαριθμητικούς χαρακτήρες, παύλες και κάτω παύλες.`,
  alpha_num: (field) => `Το πεδίο πρέπει να περιέχει μόνο αλφαριθμητικούς χαρακτήρες.`,
  alpha_spaces: (field) => `Το πεδίο μπορεί να περιέχει μόνο αλφαβητικούς χαρακτήρες και κενά.`,
  alpha: (field) => `Το πεδίο πρέπει να περιέχει μόνο αλφαβητικούς χαρακτήρες.`,
  before: (field, [target]) => `Η τιμή του αρχικού πεδίου πρέπει να είναι προγενέστερη του τελικού πεδίου.`,
  between: (field, [min, max]) => `Η τιμή του πεδίου πρέπει να είναι μεταξύ ${min} καί ${max}.`,
  confirmed: (field, [confirmedField]) => `Το πεδίo δεν ταιριάζει με το πεδίo επιβεβαίωσης.`,
  credit_card: (field) => `Το πεδίο πρέπει να περιέχει αριθμό έγκυρης πιστωτικής κάρτας.`,
  date_between: (field, [min, max]) => `Η ημερομηνία πρέπει να είναι μεταξύ ${min} καί ${max}.`,
  date_format: (field, [format]) => `Η ημερομηνία πρέπει να είναι σε μορφή ${format}.`,
  decimal: (field, [decimals] = ['*']) => `Το πεδίο πρέπει να είναι αριθμός και να περιέχει ${decimals === '*' ? '' : decimals} δεκαδικά ψηφία.`,
  digits: (field, [length]) => `Το πεδίο πρέπει να είναι αριθμός και να περιέχει ${length} ψηφία.`,
  dimensions: (field, [width, height]) => `Οι διαστάσεις πρέπει να είναι ${width} pixels επί ${height} pixels.`,
  email: (field) => `Η τιμή του πεδίου πρέπει να είναι έγκυρο email.`,
  ext: (field) => `Το πεδίο πρέπει να είναι έγκυρο αρχείο.`,
  image: (field) => `Το πεδίο πρέπει να είναι εικόνα.`,
  in: (field) => `${field} πρέπει να είναι έγκυρη τιμή.`,
  ip: (field) => `Το πεδίο IP πρέπει να είναι έγκυρη διεύθυνση IP.`,
  max: (field, [length]) => `Η τιμή του πεδίου δεν πρέπει να υπερβαίνει τους ${length} χαρακτήρες.`,
  max_value: (field, [max]) => `Η τιμή του πεδίου πρέπει να είναι ${max} ή λιγότερο.`,
  mimes: (field) => `Το πεδίο πρέπει να είναι έγκυρο αρχείο ΜΙΜΕ.`,
  min: (field, [length]) => `Η τιμή του πεδίου πρέπει να είναι τουλάχιστον ${length} χαρακτήρες.`,
  min_value: (field, [min]) => `Η τιμή του πεδίου πρέπει να είναι ${min} ή περισσότερο.`,
  not_in: (field) => `Η τιμή του πεδίου πρέπει να είναι έγκυρη τιμή.`,
  numeric: (field) => `Το πεδίο πρέπει να περιέχει μόνο αριθμούς.`,
  regex: (field) => `Το πεδίο δεν είναι έγκυρο.`,
  // Example follows
  // required: (field) => `${fields} δεν έχει συμπληρωθεί.`,
  required: (field) => `Το πεδίο δεν έχει συμπληρωθεί.`,
  // size: (field, [size]) => `${field} πρέπει να μην υπερβαίνει τα ${formatFileSize(size)}.`,
  size: (field, [size]) => `Το πεδίο δεν περέπει να υπερβαίνει τα XXXXX}.`,
  url: (field) => `Το πεδίο πρέπει να είναι έγκυρη διεύθυνση URL.`
}

const locale = {
  name: 'el',
  messages,
  attributes: {}
}

// if (isDefinedGlobally()) {
//   VeeValidate.Validator.addLocale(locale)
// }

export default locale
