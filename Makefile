all:
	xgettext -D src --files-from=lang/POTFILES -d aidger -p lang -L Java --from-code=utf-8 -j --force-po --output=aidger.pot -k_
	#msgmerge --update lang/de.po lang/aidger.pot; done

create:
	#msgcat --properties-output de.po
	



