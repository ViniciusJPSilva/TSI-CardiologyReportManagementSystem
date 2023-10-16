
/*
	Máscara do CPF.
*/
$(document).ready(function() {
	$('#cpf').mask('000.000.000-00', {
		reverse: true
	});
});

/*
	Impede que valores que não estejam no Enum ICD sejam preenchidos.
 */
$(document).ready(function() {
	function restrictToDatalistValues(inputField, datalistID) {
		$(inputField).on('input', function() {
			const enteredValue = $(this).val();
			const options = $(`${datalistID} option`).map(function() {
				return $(this).val();
			}).get();

			if (!options.includes(enteredValue)) {
				$(this).val('');
			}
		});
	}

	restrictToDatalistValues('#type', '#types');
	restrictToDatalistValues('#icd', '#idc-list');
});


/*
	Limita o limite de caracteres de determinados <textarea/>
 */
$(document).ready(function() {
	const maxChars = 250; // O número máximo de caracteres permitidos
	const textarea = $('#textArea');
	const charCount = $('#charCount');

	// Evento de entrada (quando o usuário digita no campo)
	textarea.on('input', function() {
		const currentChars = textarea.val().length;
		const remainingChars = maxChars - currentChars;

		charCount.text(remainingChars + ' caracteres restantes');

		// Se o usuário ultrapassar o limite, truncar o texto
		if (currentChars > maxChars) {
			textarea.val(textarea.val().substring(0, maxChars));
			charCount.text('0 caracteres restantes');
		}
	});
});