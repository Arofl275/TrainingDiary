package ap.trainingdiary.gui;

import java.awt.HeadlessException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.net.URL;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.plaf.FileChooserUI;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileFilter;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import javax.swing.WindowConstants;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import java.io.File;
import java.io.IOException;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.List;
import java.util.Locale;
import java.util.ArrayList;
import java.math.BigDecimal;

import ap.trainingdiary.type.AbctractComparable;
import ap.trainingdiary.type.CommentNodeUserObject;
import ap.trainingdiary.type.CommentsNodeUserObject;
import ap.trainingdiary.type.ComparableTreeNode;
import ap.trainingdiary.type.DayNodeUserObject;
import ap.trainingdiary.type.MonthNodeUserObject;
import ap.trainingdiary.type.NodeType;
import ap.trainingdiary.type.RootNodeUserObject;
import ap.trainingdiary.type.TemplateNodeUserObject;
import ap.trainingdiary.type.TemplatesNodeUserObject;
import ap.trainingdiary.type.TrainingNodeUserObject;
import ap.trainingdiary.type.TrainingsNodeUserObject;
import ap.trainingdiary.type.YearNodeUserObject;

import ap.trainingdiary.languages.ColumnType;
import ap.trainingdiary.languages.Menu;
import ap.trainingdiary.languages.NodeName;
import ap.trainingdiary.languages.Properties;
import ap.trainingdiary.languages.Title;
import ap.trainingdiary.languages.TrainingType;

import ap.trainingdiary.type.Comment;
import ap.trainingdiary.type.CommentTemplatePair;
import ap.trainingdiary.type.Template;
import ap.trainingdiary.type.Training;
import ap.trainingdiary.type.TrainingRow;
import ap.trainingdiary.json.serializer.CommentSerializer;
import ap.trainingdiary.json.serializer.TemplateSerializer;
import ap.trainingdiary.json.serializer.TrainingSerializer;
import ap.trainingdiary.json.deserializer.CommentDeserializer;
import ap.trainingdiary.json.deserializer.TemplateDeserializer;
import ap.trainingdiary.json.deserializer.TrainingDeserializer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;

import ap.filefilter.NoRecursiveFilter;
import ap.trainingdiary.filefilter.CommentsIOFileFilter;
import ap.trainingdiary.filefilter.TemplatesIOFileFilter;
import ap.trainingdiary.filefilter.TrainingsIOFileFilter;

import static ap.swing.JMenuItemBuilder.addAction;
import ap.trainingdiary.type.TrainingPath;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.layout.property.UnitValue;

import ap.trainingdiary.fonts.Fonts;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import ap.trainingdiary.languages.DateFormat;

import ap.trainingdiary.image.TrainingImages;

import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Image;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.xobject.PdfImageXObject;
import com.itextpdf.layout.borders.SolidBorder;
public class MainFrame{
	public static void show(final int windowWidth, final int windowHeight)throws HeadlessException{
		JFrameWithBounds frame = new JFrameWithBounds(Properties.getInstance().getValue(Title.TRAININGDIARY), windowWidth, windowHeight);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		TrainingDiaryTree tree = new TrainingDiaryTree();
		JScrollPane scrollPane = new JScrollPane(tree.getTree());

		Collection<File> commentFiles = getCommentFiles();
		if(commentFiles != null){
			for(File commentFile: commentFiles){
				addComment(tree, commentFile);
			}
		}
		Collection<File> templateFiles = getTemplateFiles();
		if(templateFiles != null){
			for(File templateFile: templateFiles){
				addTemplate(tree, templateFile);
			}
		}
		Collection<File> trainingFiles = getTrainingFiles();
		if(trainingFiles != null){
			for(File trainingFile: trainingFiles){
				addTraining(tree, trainingFile);
			}
		}
		frame.add(scrollPane);
		JMenuItem miAddComment = addAction(
			tree.getTree(),
			JMenuItemBuilder.addComment(),
			KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK),
			new AbstractAction(){
				public void actionPerformed(ActionEvent e){
					if(tree.isSelectionEmpty()){
						AddCommentDialog addCommentDialog = new AddCommentDialog(frame);
						addCommentDialog.setCommentNameSet(tree.getCommentNames());
						final int returnValue = addCommentDialog.show();
						if(returnValue == addCommentDialog.OK_OPTION){
							addComment(tree, addCommentDialog.getComment());
						}
					}
				}
			},
			"addComment"
		);
		JMenuItem miEditComment = addAction(
			tree.getTree(),
			JMenuItemBuilder.editComment(),
			KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK),
			new AbstractAction(){
				public void actionPerformed(ActionEvent e){
					CommentNodeUserObject cnuo = tree.getCommentNodeUserObject();
					if(cnuo != null){
						try{
							Comment comment = CommentDeserializer.load(cnuo.getFile());
							EditCommentDialog editCommentDialog = new EditCommentDialog(frame);
							editCommentDialog.setComment(comment);
							final int returnValue = editCommentDialog.show();
							if(returnValue == editCommentDialog.OK_OPTION){
								addComment(tree, editCommentDialog.getComment());
							}
						}
						catch(IOException ioe){
							ioe.printStackTrace();
						}
					}
				}
			},
			"editComment"
		);
		JMenuItem miAddTemplate = addAction(
			tree.getTree(),
			JMenuItemBuilder.addTemplate(),
			KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_DOWN_MASK),
			new AbstractAction(){
				public void actionPerformed(ActionEvent e){
					if(tree.isSelectionEmpty()){
						AddTemplateDialog addTemplateDialog = new AddTemplateDialog(frame);
						addTemplateDialog.setTemplateNameSet(tree.getTemplateNames());
						final int returnValue = addTemplateDialog.show();
						if(returnValue == addTemplateDialog.OK_OPTION){
							addTemplate(tree, addTemplateDialog.getTemplate());
						}
					}
				}
			},
			"addTemplate"
		);
		JMenuItem miEditTemplate = addAction(
			tree.getTree(),
			JMenuItemBuilder.editTemplate(),
			KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK),
			new AbstractAction(){
				public void actionPerformed(ActionEvent e){
					TemplateNodeUserObject tnuo = tree.getTemplateNodeUserObject();
					if(tnuo != null){
						try{
							final Template template = TemplateDeserializer.load(tnuo.getFile());
							EditTemplateDialog editTemplateDialog = new EditTemplateDialog(frame);
							editTemplateDialog.setTemplate(template);
							final int returnValue = editTemplateDialog.show();
							if(returnValue == editTemplateDialog.OK_OPTION){
								addTemplate(tree, editTemplateDialog.getTemplate());
							}
						}
						catch(IOException ioe){
							ioe.printStackTrace();
						}
					}
				}
			},
			"editTemplate"
		);
		JMenuItem miAddTraining = addAction(
			tree.getTree(),
			JMenuItemBuilder.addTraining(),
			KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.ALT_DOWN_MASK),
			new AbstractAction(){
				public void actionPerformed(ActionEvent e){
					if(tree.isAddTrainingCondition()){
						AddTrainingDialog addTrainingDialog = new AddTrainingDialog(frame);
						CommentTemplatePair ctp = tree.getCommentTemplatePair();
						if(ctp != null){
							CommentNodeUserObject cnuo = ctp.getCommentNodeUserObject();
							TemplateNodeUserObject tnuo = ctp.getTemplateNodeUserObject();
							if(cnuo != null){
								try{
									Comment comment = CommentDeserializer.load(cnuo.getFile());
									addTrainingDialog.setComment(comment.getText());
								}
								catch(IOException ioe){
									ioe.printStackTrace();
								}
							}
							if(tnuo != null){
								try{
									Template template = TemplateDeserializer.load(tnuo.getFile());
									addTrainingDialog.setTrainingData(template.getTrainingData());
								}
								catch(IOException ioe){
									ioe.printStackTrace();
								}
							}
						}
						addTrainingDialog.setTrainingPathSet(tree.getTrainingPaths());
						final int returnValue = addTrainingDialog.show();
						if(returnValue == addTrainingDialog.OK_OPTION){
							addTraining(tree, addTrainingDialog.getTraining());
						}
					}
				}
			},
			"addTraining"
		);
		JMenuItem miEditTraining = addAction(
			tree.getTree(),
			JMenuItemBuilder.editTraining(),
			KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK),
			new AbstractAction(){
				public void actionPerformed(ActionEvent e){
					TrainingNodeUserObject tnuo = tree.getTrainingNodeUserObject();
					if(tnuo != null){
						try{
							final Training training = TrainingDeserializer.load(tnuo.getFile());
							EditTrainingDialog editTrainingDialog = new EditTrainingDialog(frame);
							editTrainingDialog.setTraining(training);
							final int returnValue = editTrainingDialog.show();
							if(returnValue == editTrainingDialog.OK_OPTION){
								addTraining(tree, editTrainingDialog.getTraining());
							}
						}
						catch(IOException ioe){
							ioe.printStackTrace();
						}
					}
				}
			},
			"editTraining"
		);
		JMenuItem miRemove = addAction(
			tree.getTree(),
			JMenuItemBuilder.remove(),
			KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0),
			new AbstractAction(){
				public void actionPerformed(ActionEvent e){
					tree.removeAllSelectedNodes();
				}
			},
			"remove"
		);
		JMenuItem miDeselect = addAction(
			tree.getTree(),
			JMenuItemBuilder.deselect(),
			KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
			new AbstractAction(){
				public void actionPerformed(ActionEvent e){
					tree.clearSelection();
				}
			},
			"deselect"
		);
		JMenuItem miPrintToPDF = addAction(
			tree.getTree(),
			JMenuItemBuilder.printToPDF(),
			KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_DOWN_MASK),
			new AbstractAction(){
				public void actionPerformed(ActionEvent e){
					TrainingNodeUserObject tnuo = tree.getTrainingNodeUserObject();
					if(tnuo != null){
						try{
							final Training training = TrainingDeserializer.load(tnuo.getFile());
							TrainingType trainingType = TrainingType.getTrainingType(training.getTrainingIndex());
							String fileName = String.format("training_%d-%d-%d_%s.pdf", training.getYear(), (training.getMonth() + 1), training.getDay(), trainingType.getKey().replace(' ', '_'));
							JFileChooser saveFileChooser = new JFileChooser();
							saveFileChooser.setCurrentDirectory(getCurrentDirectory());
							saveFileChooser.setDialogTitle(Properties.getInstance().getValue(Title.SPECIFYAFILETOSAVE));
							FileFilter pdfFileFilter = new FileNameExtensionFilter("*.pdf", "pdf");
							saveFileChooser.addChoosableFileFilter(pdfFileFilter);
							FileFilter[] fileFilterArray = saveFileChooser.getChoosableFileFilters();
							// FileFilter allFiles = null;
							FileChooserUI ui = saveFileChooser.getUI();
							FileFilter allFiles = ui.getAcceptAllFileFilter(saveFileChooser);
							if(allFiles != null){
								saveFileChooser.removeChoosableFileFilter(allFiles);
							}
							saveFileChooser.setSelectedFile(new File(getCurrentDirectory(), fileName));
							int returnValue = saveFileChooser.showSaveDialog(frame);
							if(returnValue == JFileChooser.APPROVE_OPTION){
								File selectedFile = saveFileChooser.getSelectedFile();
								String date = createDate(training.getYear(), training.getMonth(), training.getDay());
								String sTrainingType = Properties.getInstance().getValue(trainingType);
								String sCaption = String.format("%s, %s", date, sTrainingType);
								PdfDocument pdfDoc = new PdfDocument(new PdfWriter(selectedFile.getAbsolutePath()));
								Document doc = new Document(pdfDoc);
								Table table = new Table(new float[]{50, 100, 100, 180, 110});
								final float width = 1.5f;
								table.addStyle(new Style().setBorder(new SolidBorder(ColorConstants.BLACK, width)));
								table.setFontSize(12);
								table.addHeaderCell(createDivCenter("N"));
								table.addHeaderCell(createDivCenter(Properties.getInstance().getValue(ColumnType.WEIGHTKG)));
								table.addHeaderCell(createDivCenter(Properties.getInstance().getValue(ColumnType.WEIGHTLB)));
								table.addHeaderCell(createDivCenter(Properties.getInstance().getValue(ColumnType.REPEATS)));
								table.addHeaderCell(createDivCenter(Properties.getInstance().getValue(ColumnType.DONE)));
								int count = 0;
								for(TrainingRow row: training.getTrainingData()){
									table.addCell(createDivRight(Integer.valueOf(++count).toString()));
									table.addCell(createDivRight(row.getWeightKGToString()));
									table.addCell(createDivRight(row.getWeightLBToString()));
									table.addCell(createDivRight(row.getRepeatsToString()));
									table.addCell(new Div());
								}
								doc.add(createDivLeftWithBorder(sCaption, width));
								doc.add(createImage(training.getTrainingIndex()));
								doc.add(table);
								doc.add(createDivLeft(training.getComment()));
								doc.close();
							}
						}
						catch(IOException ioe){
							ioe.printStackTrace();
						}
					}
				}
			},
			"printToPDF"
		);
		JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.addPopupMenuListener(
			new PopupMenuListener(){
				public void popupMenuCanceled(PopupMenuEvent e){}
				public void popupMenuWillBecomeInvisible(PopupMenuEvent e){}
				public void popupMenuWillBecomeVisible(PopupMenuEvent e){
					popupMenu.removeAll();
					if(tree.isSelectionEmpty()){
						popupMenu.add(miAddComment);
						popupMenu.add(miAddTemplate);
						popupMenu.add(miAddTraining);
					}
					else if(tree.isAddTrainingCondition()){
						popupMenu.add(miAddTraining);
						if(tree.isSingleListSelected()){
							NodeType nodeType = tree.getNodeTypeOfSingleList();
							if(nodeType == NodeType.COMMENTNODEUSEROBJECT){
								popupMenu.add(miEditComment);
							}
							else if(nodeType == NodeType.TEMPLATENODEUSEROBJECT){
								popupMenu.add(miEditTemplate);
							}
						}
						popupMenu.add(miDeselect);
						popupMenu.add(miRemove);
					}
					else if(tree.isSingleListSelected()){
						NodeType nodeType = tree.getNodeTypeOfSingleList();
						if(nodeType == NodeType.TRAININGNODEUSEROBJECT){
							popupMenu.add(miEditTraining);
							popupMenu.add(miPrintToPDF);
						}
						popupMenu.add(miDeselect);
						popupMenu.add(miRemove);
					}
					else{
						popupMenu.add(miDeselect);
						popupMenu.add(miRemove);
					}
				}
			}
		);
		tree.getTree().setComponentPopupMenu(popupMenu);
		scrollPane.setComponentPopupMenu(popupMenu);
		frame.setBounds();
		frame.setVisible(true);
	}
	private static File getCurrentDirectory(){
		return new File( "." );
	}
	private static File getCommentsPath(){
		return new File(getCurrentDirectory(), "comments");
	}
	private static File getTemplatesPath(){
		return new File(getCurrentDirectory(), "templates");
	}
	private static File getTrainingsPath(){
		return new File(getCurrentDirectory(), "trainings");
	}
	private static Collection<File> getCommentFiles(){
		if(getCommentsPath().exists()){
			return FileUtils.listFiles(getCommentsPath(), CommentsIOFileFilter.getFileFilter(), NoRecursiveFilter.getFileFilter());
		}
		return null;
	}
	private static Collection<File> getTemplateFiles(){
		if(getTemplatesPath().exists()){
			return FileUtils.listFiles(getTemplatesPath(), TemplatesIOFileFilter.getFileFilter(), NoRecursiveFilter.getFileFilter());
		}
		return null;
	}
	private static Collection<File> getTrainingFiles(){
		if(getTrainingsPath().exists()){
			return FileUtils.listFiles(getTrainingsPath(), TrainingsIOFileFilter.getFileFilter(), NoRecursiveFilter.getFileFilter());
		}
		return null;
	}

	private static void addComment(final TrainingDiaryTree tree, final Comment comment){
		File commentPath = new File(getCommentsPath(), String.format("comment%d.json", System.currentTimeMillis()));
		try{
			CommentSerializer.write(commentPath, comment);
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
		tree.addComment(comment.getName(), commentPath);
	}
	private static void addComment(final TrainingDiaryTree tree, final File path){
		try{
			Comment comment = CommentDeserializer.load(path);
			tree.addComment(comment.getName(), path);
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	private static void addTemplate(final TrainingDiaryTree tree, final Template template){
		File templatePath = new File(getTemplatesPath(), String.format("template%d.json", System.currentTimeMillis()));
		try{
			TemplateSerializer.write(templatePath, template);
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
		tree.addTemplate(template.getName(), templatePath);
	}
	private static void addTemplate(final TrainingDiaryTree tree, final File path){
		try{
			Template template = TemplateDeserializer.load(path);
			tree.addTemplate(template.getName(), path);
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	private static void addTraining(final TrainingDiaryTree tree, final Training training){
		File trainingPath = new File(getTrainingsPath(), String.format("training%d.json", System.currentTimeMillis()));
		try{
			TrainingSerializer.write(trainingPath, training);
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
		tree.addTraining(training.getYear(), training.getMonth(), training.getDay(), training.getTrainingIndex(), trainingPath);
	}
	private static void addTraining(final TrainingDiaryTree tree, final File path){
		try{
			Training training = TrainingDeserializer.load(path);
			tree.addTraining(training.getYear(), training.getMonth(), training.getDay(), training.getTrainingIndex(), path);
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	private static Div createDivRight(final String text) throws IOException{
		return new Div().add(
			new Paragraph(text)
			.setFont(Fonts.arial())
			.setFontSize(12)
			.setBackgroundColor(ColorConstants.WHITE)
			.setFontColor(ColorConstants.BLACK)
			.setTextAlignment(TextAlignment.RIGHT)
		);
	}
	private static Div createDivLeft(final String text) throws IOException{
		return new Div().add(
			new Paragraph(text)
			.setFont(Fonts.arial())
			.setFontSize(12)
			.setBackgroundColor(ColorConstants.WHITE)
			.setFontColor(ColorConstants.BLACK)
			.setTextAlignment(TextAlignment.LEFT)
		);
	}
	private static Div createDivLeftWithBorder(final String text, final float width) throws IOException{
		Paragraph paragraph = new Paragraph(text)
		.setFont(Fonts.arial())
		.setFontSize(12)
		.setBackgroundColor(ColorConstants.WHITE)
		.setFontColor(ColorConstants.BLACK)
		.setTextAlignment(TextAlignment.LEFT)
		.setBorder(new SolidBorder(ColorConstants.BLACK, width));
		return new Div().add(paragraph);
	}
	private static String createDate(final int year, final int month, final int day) throws IOException{
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day, 0, 0);
		return new SimpleDateFormat(Properties.getInstance().getValue(DateFormat.DATEFORMAT)).format(calendar.getTime());
	}
	private static Div createImage(final int index) throws IOException{
		PdfImageXObject xObject = new PdfImageXObject(ImageDataFactory.create(TrainingImages.getInstance().getImageBytes(index)));
		Image image = new Image(xObject, 100);
		Paragraph paragraph = new Paragraph();
		paragraph.add(image);
		return new Div().add(paragraph);
	}
	private static Div createDivCenter(final String text) throws IOException{
		return new Div().add(
			new Paragraph(text)
			.setFont(Fonts.arial())
			.setFontSize(12)
			.setBackgroundColor(ColorConstants.WHITE)
			.setFontColor(ColorConstants.BLACK)
			.setTextAlignment(TextAlignment.CENTER)
		);
	}
}
