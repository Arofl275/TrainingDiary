package ap.trainingdiary.type;

public class CommentTemplatePair{
	private final CommentNodeUserObject cnuo;
	private final TemplateNodeUserObject tnuo;
	public CommentTemplatePair(
		final CommentNodeUserObject cnuo,
		final TemplateNodeUserObject tnuo
	){
		this.cnuo = cnuo;
		this.tnuo = tnuo;
	}
	public CommentNodeUserObject getCommentNodeUserObject(){
		return cnuo;
	}
	public TemplateNodeUserObject getTemplateNodeUserObject(){
		return tnuo;
	}
}
