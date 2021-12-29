# pdf_add_toc
Goal: Given a PDF and a CSV-formatted table of contents, append the contents of the CSV to the beginning of the PDF as a table of contents.

Problem: There was a textbook that was three volumes and split into three PDFs. The overall table of contents was strangely formatted, so I wanted to merge the three PDFs and then append my own Table of Contents, which would be added in a standard format.

Possible additional features:
1. Have all actions be performed entirely in memory, instead of requiring the writing and then deletion of temporary files.
2. Allow the Table of Contents to be added anywhere in the PDF file (this would need to be an additional parameter).
