package ap.trainingdiary.languages;

import java.io.File;
import java.io.IOException;

import ap.util.property.PropertyNode;
import ap.util.property.PropertyNotFoundException;
import ap.util.property.PropertyPath;
import ap.util.property.PropertyTree;
import ap.util.property.PropertyTreeDeserializer;
import ap.util.property.PropertyTreeSerializer;

import ap.trainingdiary.languages.Button;
import ap.trainingdiary.languages.ButtonKeys;
import ap.trainingdiary.languages.ColumnKeys;
import ap.trainingdiary.languages.ColumnType;
import ap.trainingdiary.languages.DateFormat;
import ap.trainingdiary.languages.DateFormatKeys;
import ap.trainingdiary.languages.ErrorKeys;
import ap.trainingdiary.languages.ErrorType;
import ap.trainingdiary.languages.Menu;
import ap.trainingdiary.languages.MenuKeys;
import ap.trainingdiary.languages.Message;
import ap.trainingdiary.languages.MessageKeys;
import ap.trainingdiary.languages.Month;
import ap.trainingdiary.languages.MonthKeys;
import ap.trainingdiary.languages.NodeName;
import ap.trainingdiary.languages.NodeKeys;
import ap.trainingdiary.languages.Title;
import ap.trainingdiary.languages.TitleKeys;
import ap.trainingdiary.languages.TrainingType;
import ap.trainingdiary.languages.TrainingTypeKeys;

public class GenerateProperties{
	private static final File packageName = new File("ap/trainingdiary/languages");
	private static final String fileName = "Properties.json";
	private static final String[] languages = new String[]{"de", "ru", "us"};

	private static final String[] buttonsDE = new String[]{
		"Abbrechen",
		"Nein",
		ButtonKeys.OK,
		"Ja"
	};
	private static final String[] buttonsRU = new String[]{
		"Отмена",
		"Нет",
		ButtonKeys.OK,
		"Да"
		
	};
	private static final String[] buttonsUS = new String[]{
		ButtonKeys.CANCEL,
		ButtonKeys.NO,
		ButtonKeys.OK,
		ButtonKeys.YES
	};
	private static final String[][] buttonsList = new String[][]{buttonsDE, buttonsRU, buttonsUS};

	private static final String[] columnsDE = new String[]{
		"Erledigt",
		"Anzahl der Wiederholungen",
		"Gewicht [kg]",
		"Gewicht [lb]",
		"Bezeichnung des Kommentars",
		"30",
		"Bezeichnung der Vorlage",
		"33"
	};
	private static final String[] columnsRU = new String[]{
		"Выполнено",
		"Колличество повторений",
		"Вес [kg]",
		"Вес [lb]",
		"Название комментария",
		"34",
		"Название шаблона",
		"37"
	};
	private static final String[] columnsUS = new String[]{
		ColumnKeys.DONE,
		ColumnKeys.REPEATS,
		ColumnKeys.WEIGHTKG,
		ColumnKeys.WEIGHTLB,
		ColumnKeys.NAMEOFCOMMENT,
		ColumnKeys.NAMEOFCOMMENTCOLUMNS,
		ColumnKeys.NAMEOFTEMPLATE,
		ColumnKeys.NAMEOFTEMPLATECOLUMNS
		
	};
	private static final String[][] columnsList = new String[][]{columnsDE, columnsRU, columnsUS};
	
	private static final String[] dateformatesDE = new String[]{
		"dd.MM.yyyy"
	};
	private static final String[] dateformatesRU = new String[]{
		"dd.MM.yyyy"
	};
	private static final String[] dateformatesUS = new String[]{
		DateFormatKeys.DATEFORMAT
	};
	private static final String[][] dateformatesList = new String[][]{dateformatesDE, dateformatesRU, dateformatesUS};
	
	private static final String[] errorsDE = new String[]{
		ErrorKeys.REPEATSFORMAT,
		ErrorKeys.WEIGHTFORMAT
	};
	private static final String[] errorsRU = new String[]{
		"Формат: n > 0",
		"Формат: 10, 10.5 or 10.25"
	};
	private static final String[] errorsUS = new String[]{
		ErrorKeys.REPEATSFORMAT,
		ErrorKeys.WEIGHTFORMAT
	};
	private static final String[][] errorsList = new String[][]{errorsDE, errorsRU, errorsUS};

	public static final String removeAllDE = "alle entfernen";
	public static final String removeAllRU = "удалить все";
	public static final String removeAllUS = "remove all";

	private static final String[] messagesDE = new String[]{
		"Der Kommentar mit dem Namen \"%s\" existiert bereits. Überschreiben?",
		"Die Vorlage mit dem Namen \"%s\" existiert bereits. Überschreiben?",
		"Das Training \"%s\" am %s existiert bereits. Überschreiben?"
	};
	private static final String[] messagesRU = new String[]{
		"Комментарий с именем \"%s\" уже существует. Переписать?",
		"Шаблон с именем \"%s\" уже существует. Переписать?",
		"Тренировка \"%s\" %s уже существует. Переписать?"
	};
	private static final String[] messagesUS = new String[]{
		MessageKeys.COMMENTEXISTS,
		MessageKeys.TEMPLATEEXISTS,
		MessageKeys.TRAININGEXISTS
	};
	private static final String[][] messagesList = new String[][]{messagesDE, messagesRU, messagesUS};
	
	private static final String[] menusDE = new String[]{
		"Kommentar hinzufügen",
		"Zeile hinzufügen",
		"Vorlage hinzufügen",
		"Training hinzufügen",
		"Auswahl aufheben",
		"Kommentar bearbeiten",
		"Zeile bearbeiten",
		"Vorlage bearbeiten",
		"Training bearbeiten",
		"Nach oben",
		"Nach unten",
		"Entfernen",
		"Alle ntfernen",
		"Als PDF drucken"
	};
	private static final String[] menusRU = new String[]{
		"Добавить коментарий",
		"Добавить строку",
		"Добавить шаблон",
		"Добавить тренировку",
		"Отменить выбор",
		"Редактировать коментарий",
		"Редактировать строку",
		"Редактировать шаблон",
		"Редактировать тренировку",
		"Вниз",
		"Верх",
		"Удалить",
		"Удалить все",
		"Распечатать в PDF"
	};
	private static final String[] menusUS = new String[]{
		MenuKeys.ADDCOMMENT,
		MenuKeys.ADDROW,
		MenuKeys.ADDTEMPLATE,
		MenuKeys.ADDTRAINING,
		MenuKeys.DESELECT,
		MenuKeys.EDITCOMMENT,
		MenuKeys.EDITROW,
		MenuKeys.EDITTEMPLATE,
		MenuKeys.EDITTRAINING,
		MenuKeys.MOVEDOWN,
		MenuKeys.MOVEUP,
		MenuKeys.REMOVE,
		MenuKeys.REMOVEALL,
		MenuKeys.PRINTTOPDF
	};
	private static final String[][] menusList = new String[][]{menusDE, menusRU, menusUS};

	private static final String[] monthsDE = new String[]{
		"Januar",
		"Februar",
		"März",
		"April",
		"Mai",
		"Juni",
		"Juli",
		"August",
		"September",
		"Oktober",
		"November",
		"December"
	};
	private static final String[] monthsRU = new String[]{
		"Январь",
		"Февраль",
		"Март",
		"Апрель",
		"Май",
		"Июнь",
		"Июль",
		"Август",
		"Сентябрь",
		"Октябрь",
		"Ноябрь",
		"Декабрь"
	};
	private static final String[] monthsUS = new String[]{
		MonthKeys.JANUARY,
		MonthKeys.FEBRUARY,
		MonthKeys.MARCH,
		MonthKeys.APRIL,
		MonthKeys.MAY,
		MonthKeys.JUNE,
		MonthKeys.JULY,
		MonthKeys.AUGUST,
		MonthKeys.SEPTEMBER,
		MonthKeys.OCTOBER,
		MonthKeys.NOVEMBER,
		MonthKeys.DECEMBER
	};
	private static final String[][] monthsList = new String[][]{monthsDE, monthsRU, monthsUS};
	
	private static final String[] nodeNamesDE = new String[]{
		"Bemerkungen",
		"Vorlagen",
		NodeKeys.TRAININGS
	};
	private static final String[] nodeNamesRU = new String[]{
		"Комментарии",
		"Шаблоны",
		"Тренировки"
	};
	private static final String[] nodeNamesUS = new String[]{
		NodeKeys.COMMENTS,
		NodeKeys.TEMPLATES,
		NodeKeys.TRAININGS
	};
	private static final String[][] nodeNamesList = new String[][]{nodeNamesDE, nodeNamesRU, nodeNamesUS};

	private static final String[] titlesDE = new String[]{
		"Kommentar hinzufügen",
		"Zeile hinzufügen",
		"Vorlage hinzufügen",
		"Training hinzufügen",
		"Kommentar bearbeiten",
		"Zeile bearbeiten",
		"Vorlage bearbeiten",
		"Training bearbeiten",
		"Warnung",
		"Sporttagebuch",
		"Geben Sie einen Dateinamen an"
	};
	private static final String[] titlesRU = new String[]{
		"Добавить коментарий",
		"Добавить строку",
		"Добавить шаблон",
		"Добавить тренеровку",
		"Редактировать коментарий",
		"Редактировать строку",
		"Редактировать шаблон",
		"Редактировать тренеровку",
		"Предупреждение",
		"Дневник по спорту",
		"Укажите название файла",
	};
	private static final String[] titlesUS = new String[]{
		TitleKeys.ADDCOMMENT,
		TitleKeys.ADDROW,
		TitleKeys.ADDTEMPLATE,
		TitleKeys.ADDTRAINING,
		TitleKeys.EDITCOMMENT,
		TitleKeys.EDITROW,
		TitleKeys.EDITTEMPLATE,
		TitleKeys.EDITTRAINING,
		TitleKeys.WARNING,
		TitleKeys.TRAININGDIARY,
		TitleKeys.SPECIFYAFILETOSAVE
	};
	private static final String[][] titlesList = new String[][]{titlesDE, titlesRU, titlesUS};

	private static final String[] trainingTypesDE = new String[]{
		"Bankdrücken",
		"Kreuzheben",
		"Kniebeuge"
	};
	private static final String[] trainingTypesRU = new String[]{
		"Жим лёжа",
		"Становая тяга",
		"Приседания"
	};
	private static final String[] trainingTypesUS = new String[]{
		TrainingTypeKeys.BENCHPRESS,
		TrainingTypeKeys.DEADLIFT,
		TrainingTypeKeys.SQUATS
	};
	private static final String[][] trainingTypesList = new String[][]{trainingTypesDE, trainingTypesRU, trainingTypesUS};

	private static File getFile(final String language){
		return new File(new File(packageName, language), fileName);
	}
	private static void addButtons(final PropertyTree propertyTree, final String[] buttons){
		for(int i = 0; i < buttons.length; i++){
			propertyTree.setValue(Button.getButton(i).getPropertyPath(), buttons[i]);
		}
	}
	private static void addColumns(final PropertyTree propertyTree, final String[] columns){
		for(int i = 0; i < columns.length; i++){
			propertyTree.setValue(ColumnType.getColumnType(i).getPropertyPath(), columns[i]);
		}
	}
	private static void addDateFormates(final PropertyTree propertyTree, final String[] dateformates){
		for(int i = 0; i < dateformates.length; i++){
			propertyTree.setValue(DateFormat.getDateFormat(i).getPropertyPath(), dateformates[i]);
		}
	}
	private static void addErrors(final PropertyTree propertyTree, final String[] errors){
		for(int i = 0; i < errors.length; i++){
			propertyTree.setValue(ErrorType.getErrorType(i).getPropertyPath(), errors[i]);
		}
	}
	private static void addMenus(final PropertyTree propertyTree, final String[] menus){
		for(int i = 0; i < menus.length; i++){
			propertyTree.setValue(Menu.getMenu(i).getPropertyPath(), menus[i]);
		}
	}
	private static void addMessages(final PropertyTree propertyTree, final String[] messages){
		for(int i = 0; i < messages.length; i++){
			propertyTree.setValue(Message.getMessage(i).getPropertyPath(), messages[i]);
		}
	}
	private static void addMonths(final PropertyTree propertyTree, final String[] months){
		for(int i = 0; i < months.length; i++){
			propertyTree.setValue(Month.getMonth(i).getPropertyPath(), months[i]);
		}
	}
	private static void addNodeNames(final PropertyTree propertyTree, final String[] nodeNames){
		for(int i = 0; i < nodeNames.length; i++){
			propertyTree.setValue(NodeName.getNodeName(i).getPropertyPath(), nodeNames[i]);
		}
	}
	private static void addTitles(final PropertyTree propertyTree, final String[] titles){
		for(int i = 0; i < titles.length; i++){
			propertyTree.setValue(Title.getTitle(i).getPropertyPath(), titles[i]);
		}
	}
	private static void addTrainingTypes(final PropertyTree propertyTree, final String[] trainingTypes){
		for(int i = 0; i < trainingTypes.length; i++){
			propertyTree.setValue(TrainingType.getTrainingType(i).getPropertyPath(), trainingTypes[i]);
		}
	}
	private static void serializeProperties(
		final String language,
		final String[] buttons,
		final String[] columns,
		final String[] dateformates,
		final String[] errors,
		final String[] menus,
		final String[] messages,
		final String[] months,
		final String[] nodeNames,
		final String[] titles,
		final String[] trainingTypes
	){
		final PropertyTree propertyTree = new PropertyTree();
		addButtons(propertyTree, buttons);
		addColumns(propertyTree, columns);
		addDateFormates(propertyTree, dateformates);
		addErrors(propertyTree, errors);
		addMenus(propertyTree, menus);
		addMessages(propertyTree, messages);
		addMonths(propertyTree, months);
		addNodeNames(propertyTree, nodeNames);
		addTitles(propertyTree, titles);
		addTrainingTypes(propertyTree, trainingTypes);
		try{
			PropertyTreeSerializer.write(getFile(language), propertyTree);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		for(int i = 0; i < languages.length; i++){
			final String language = languages[i];
			final String[] buttons = buttonsList[i];
			final String[] columns = columnsList[i];
			final String[] dateformates = dateformatesList[i];
			final String[] errors = errorsList[i];
			final String[] menus = menusList[i];
			final String[] messages = messagesList[i];
			final String[] months = monthsList[i];
			final String[] nodeNames = nodeNamesList[i];
			final String[] titles = titlesList[i];
			final String[] trainingTypes = trainingTypesList[i];
			serializeProperties(
				language,
				buttons,
				columns,
				dateformates,
				errors,
				menus,
				messages,
				months,
				nodeNames,
				titles,
				trainingTypes
			);
		}
	}
}
